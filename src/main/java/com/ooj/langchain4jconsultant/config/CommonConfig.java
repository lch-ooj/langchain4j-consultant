package com.ooj.langchain4jconsultant.config;

import dev.langchain4j.community.store.embedding.redis.RedisEmbeddingStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfig {

    //@Autowired报红是因为当前类想要从ioc容器中获取对象，自己需要成为ioc容器中的对象。
    //需要在类上添加注解，成为ioc容器中的对象
    @Autowired
    private ChatMemoryStore redisChatMemoryStore;
    @Autowired
    private ChatMemoryStore mongoChatMemoryStore;

    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private RedisEmbeddingStore redisEmbeddingStore;


    //构建会话记忆提供者对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider(){
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(10)
                        .chatMemoryStore(mongoChatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;
    }

    /**
     * 构建向量数据库操作对象
     */
//    @Bean
    public EmbeddingStore store(){
        //1、加载文档进内存（文档加载器与解析器）
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content",new ApachePdfBoxDocumentParser()); //指定文档解析器
        //2.1 构建文档分割器对象（文档分割器）
        DocumentSplitter documentSplitter = DocumentSplitters.recursive(500,100);
        //2.2 构建向量数据库操作对象（内存版本）
//        InMemoryEmbeddingStore store = new InMemoryEmbeddingStore();

        //3、构建一个EmbeddingStoreIngestor对象，进行文本数据切割、向量化、存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentSplitter)
                .embeddingModel(embeddingModel)  //向量模型
                .embeddingStore(redisEmbeddingStore)  //向量数据库
                .build();
        ingestor.ingest(documents);
        return redisEmbeddingStore;
    }

    //构建向量数据库检索对象
    @Bean
    public ContentRetriever contentRetriever(){
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(redisEmbeddingStore)
                .embeddingModel(embeddingModel)
                .minScore(0.5)
                .maxResults(3)
                .build();
    }










}



















