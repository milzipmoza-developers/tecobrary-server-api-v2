//package common.util;
//
//import com.woowacourse.tecobrary.librarybook.application.api.ESRestClient;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.support.WriteRequest;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import static com.woowacourse.tecobrary.librarybook.domain.ESLibraryBookRepository.LIBRARY_BOOKS_INDEX_NAME;
//
//public class ESAcceptanceTestUtils extends AcceptanceTestUtils {
//
//    private static final Logger log = LoggerFactory.getLogger(ESAcceptanceTestUtils.class);
//
//    private static final String ES_LIBRARYBOOKS_INIT_FILE_PATH = "/es_librarybooks_init";
//    private static final String ES_LIBRARYBOOKS_MAPPING_JSON_FILE_PATH = "/es_librarybooks_mapping.json";
//
//    @Autowired
//    private ESRestClient esRestClient;
//
//    private CreateIndexRequest createIndexRequest;
//    private BulkRequest createDocumentsRequest;
//    private DeleteIndexRequest deleteIndexRequest;
//
//    @BeforeEach
//    void setUp() {
//        try {
//            createIndex();
//            createDocuments();
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new ESAcceptanceTestException(e);
//        }
//    }
//
//    private void createIndex() throws IOException {
//        if (createIndexRequest == null) {
//            createIndexRequest = new CreateIndexRequest(LIBRARY_BOOKS_INDEX_NAME);
//            String jsonMappingLines = "";
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                    getClass().getResourceAsStream(ES_LIBRARYBOOKS_MAPPING_JSON_FILE_PATH)))) {
//                jsonMappingLines = jsonMappingLines + br.readLine();
//            }
//            createIndexRequest.source(jsonMappingLines, XContentType.JSON);
//        }
//
//        esRestClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//    }
//
//    private void createDocuments() throws IOException {
//        if (createDocumentsRequest == null) {
//            createDocumentsRequest = new BulkRequest();
//
//            String id;
//            String properties;
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                    getClass().getResourceAsStream(ES_LIBRARYBOOKS_INIT_FILE_PATH)))) {
//                while ((id = br.readLine()) != null) {
//                    properties = br.readLine();
//                    createDocumentsRequest.add(new UpdateRequest(LIBRARY_BOOKS_INDEX_NAME, id)
//                            .doc(properties, XContentType.JSON)
//                            .docAsUpsert(true))
//                            .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
//                }
//            }
//        }
//
//        esRestClient.bulk(createDocumentsRequest, RequestOptions.DEFAULT);
//    }
//
//    @AfterEach
//    void tearDown() {
//        try {
//            deleteIndex();
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new ESAcceptanceTestException(e);
//        }
//    }
//
//    private void deleteIndex() throws IOException {
//        if (deleteIndexRequest == null) {
//            deleteIndexRequest = new DeleteIndexRequest(LIBRARY_BOOKS_INDEX_NAME);
//        }
//
//        esRestClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//    }
//}
