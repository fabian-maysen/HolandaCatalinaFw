package org.hcjf.properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hcjf.layers.locale.DefaultLocaleLayer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This class overrides the system properties default implementation adding
 * some default values and properties definitions for the service-oriented platforms
 * works.
 * @author javaito
 */
public final class SystemProperties extends Properties {

    public static final String HCJF_DEFAULT_DATE_FORMAT = "hcjf.default.date.format";
    public static final String HCJF_DEFAULT_NUMBER_FORMAT = "hcjf.default.number.format";
    public static final String HCJF_DEFAULT_DECIMAL_SEPARATOR = "hcjf.default.decimal.separator";
    public static final String HCJF_DEFAULT_GROUPING_SEPARATOR = "hcjf.default.grouping.separator";
    public static final String HCJF_DEFAULT_LOCALE = "hcjf.default.locale";
    public static final String HCJF_DEFAULT_LOCALE_LAYER_IMPLEMENTATION = "hcjf.default.locale.layer.implementation";
    public static final String HCJF_DEFAULT_LOCALE_LAYER_IMPLEMENTATION_NAME = "hcjf.default.locale.layer.implementation.name";
    public static final String HCJF_DEFAULT_PROPERTIES_FILE_PATH = "hcjf.default.properties.file.path";
    public static final String HCJF_DEFAULT_PROPERTIES_FILE_XML = "hcjf.default.properties.file.xml";
    public static final String HCJF_UUID_REGEX = "hcjf.uuid.regex";
    public static final String HCJF_INTEGER_NUMBER_REGEX = "hcjf.integer.number.regex";
    public static final String HCJF_DECIMAL_NUMBER_REGEX = "hcjf.decimal.number.regex";
    public static final String HCJF_SCIENTIFIC_NUMBER_REGEX = "hcjf.scientific.number.regex";

    public static final class Layer {
        public static final String LOG_TAG = "hcjf.layers.log.tag";

        public static final class Deployment {
            public static final String SERVICE_NAME = "hcjf.layers.deployment.service.name";
            public static final String SERVICE_PRIORITY = "hcjf.layers.deployment.service.priority";
            public static final String CLOUD_DEPLOYMENT_ENABLED = "hcjf.layers.plugin.cloud.deployment.enabled";
            public static final String CLOUD_DEPLOYMENT_MAP_NAME = "hcjf.layers.plugin.cloud.deployment.map.name";
            public static final String CLOUD_DEPLOYMENT_LOCK_NAME = "hcjf.layers.plugin.cloud.deployment.lock.name";
            public static final String CLOUD_DEPLOYMENT_LOCK_CONDITION_NAME = "hcjf.layers.plugin.cloud.deployment.lock.condition.name";
            public static final String CLOUD_DEPLOYMENT_FILTER = "hcjf.layers.plugin.cloud.deployment.filter";
        }
    }

    public static final class Service {
        public static final String STATIC_THREAD_NAME = "hcjf.service.static.thread.name";
        public static final String STATIC_THREAD_POOL_CORE_SIZE = "hcjf.service.static.thread.pool.core.size";
        public static final String STATIC_THREAD_POOL_MAX_SIZE = "hcfj.service.static.thread.pool.max.size";
        public static final String STATIC_THREAD_POOL_KEEP_ALIVE_TIME = "hcfj.service.static.thread.pool.keep.alive.time";
        public static final String THREAD_POOL_CORE_SIZE = "hcjf.service.thread.pool.core.size";
        public static final String THREAD_POOL_MAX_SIZE = "hcfj.service.thread.pool.max.size";
        public static final String THREAD_POOL_KEEP_ALIVE_TIME = "hcfj.service.thread.pool.keep.alive.time";
        public static final String GUEST_SESSION_NAME = "hcjf.service.guest.session.name";
        public static final String SYSTEM_SESSION_NAME = "hcjf.service.system.session.name";
        public static final String SHUTDOWN_TIME_OUT = "hcjf.service.shutdown.time.out";
    }

    public static final class Event {
        public static final String SERVICE_NAME = "hcjf.event.service.name";
        public static final String SERVICE_PRIORITY = "hcjf.event.service.priority";
    }

    public static final class Log {
        public static final String SERVICE_NAME = "hcjf.log.service.name";
        public static final String SERVICE_PRIORITY = "hcjf.log.service.priority";
        public static final String FILE_PREFIX = "hcfj.log.file.prefix";
        public static final String ERROR_FILE = "hcfj.log.error.file";
        public static final String WARNING_FILE = "hcfj.log.warning.file";
        public static final String INFO_FILE = "hcfj.log.info.file";
        public static final String DEBUG_FILE = "hcfj.log.debug.file";
        public static final String LEVEL = "hcfj.log.level";
        public static final String DATE_FORMAT = "hcfj.log.date.format";
        public static final String CONSUMERS = "hcjf.log.consumers";
        public static final String SYSTEM_OUT_ENABLED = "hcjf.log.system.out.enabled";
        public static final String QUEUE_INITIAL_SIZE = "hcjf.log.queue.initial.size";
        public static final String TRUNCATE_TAG = "hcjf.log.truncate.tag";
        public static final String TRUNCATE_TAG_SIZE = "hcjf.log.truncate.tag.size";
        public static final String LOG_CONSUMERS_SIZE = "hcjf.log.consumers.size";
    }

    public static final class Encoding {
        public static final String SERVICE_NAME = "hcjf.encoding.service.name";
        public static final String SERVICE_PRIORITY = "hcjf.encoding.service.priority";
    }

    public static final class FileSystem {
        public static final String SERVICE_NAME = "hcjf.file.system.service.name";
        public static final String SERVICE_PRIORITY = "hcjf.file.system.service.priority";
        public static final String LOG_TAG = "hcjf.file.system.log.tag";
    }

    public static final class Net {
        public static final String INPUT_BUFFER_SIZE = "hcfj.net.input.buffer.size";
        public static final String OUTPUT_BUFFER_SIZE = "hcfj.net.output.buffer.size";
        public static final String DISCONNECT_AND_REMOVE = "hcfj.net.disconnect.and.remove";
        public static final String CONNECTION_TIMEOUT_AVAILABLE = "hcfj.net.connection.timeout.available";
        public static final String CONNECTION_TIMEOUT = "hcfj.net.connection.timeout";
        public static final String WRITE_TIMEOUT = "hcjf.net.write.timeout";
        public static final String IO_THREAD_POOL_KEEP_ALIVE_TIME = "hcjf.net.io.thread.pool.keep.alive.time";
        public static final String IO_THREAD_POOL_MAX_SIZE = "hcjf.net.io.thread.pool.max.size";
        public static final String IO_THREAD_POOL_CORE_SIZE = "hcjf.net.io.thread.pool.core.size";
        public static final String DEFAULT_INPUT_BUFFER_SIZE = "hcjf.net.default.input.buffer.size";
        public static final String DEFAULT_OUTPUT_BUFFER_SIZE = "hcjf.net.default.output.buffer.size";
        public static final String IO_THREAD_DIRECT_ALLOCATE_MEMORY = "hcjf.net.io.thread.direct.allocate.memory";
        public static final String SSL_MAX_IO_THREAD_POOL_SIZE = "hcjf.net.ssl.max.io.thread.pool.size";

        public static final class Ssl {
            public static final String DEFAULT_PROTOCOL = "hcjf.net.ssl.default.protocol";
            public static final String IO_THREAD_NAME = "hcjf.net.ssl.io.thread.name";
            public static final String ENGINE_THREAD_NAME = "hcjf.net.ssl.engine.thread.name";
            public static final String DEFAULT_KEYSTORE_PASSWORD = "hcjf.net.ssl.default.keystore.password";
            public static final String DEFAULT_KEY_PASSWORD = "hcjf.net.ssl.default.key.password";
            public static final String DEFAULT_KEYSTORE_FILE_PATH = "hcjf.net.ssl.default.keystore.file.path";
            public static final String DEFAULT_TRUSTED_CERTS_FILE_PATH = "hcjf.net.ssl.default.trusted.certs.file.path";
            public static final String DEFAULT_KEY_TYPE = "hcjf.net.ssl.default.key.type";

        }

        public static final class Http {
            public static final String SERVER_NAME = "hcjf.net.http.server.name";
            public static final String RESPONSE_DATE_HEADER_FORMAT_VALUE = "hcjf.net.http.response.date.header.format.value";
            public static final String INPUT_LOG_BODY_MAX_LENGTH = "hcjf.net.http.input.log.body.max.length";
            public static final String OUTPUT_LOG_BODY_MAX_LENGTH = "hcjf.net.http.output.log.body.max.length";
            public static final String DEFAULT_SERVER_PORT = "hcjf.net.http.default.server.port";
            public static final String DEFAULT_CLIENT_PORT = "hcjf.net.http.default.client.port";
            public static final String STREAMING_LIMIT_FILE_SIZE = "hcjf.net.http.streaming.limit.file.size";
            public static final String DEFAULT_ERROR_FORMAT_SHOW_STACK = "hcjf.net.http.default.error.format.show.stack";
            public static final String DEFAULT_CLIENT_CONNECT_TIMEOUT = "hcjf.net.http.default.client.connect.timeout";
            public static final String DEFAULT_CLIENT_READ_TIMEOUT = "hcjf.net.http.default.client.read.timeout";
            public static final String DEFAULT_CLIENT_WRITE_TIMEOUT = "hcjf.net.http.default.client.write.timeout";
            public static final String DEFAULT_GUEST_SESSION_NAME = "hcjf.net.http.default.guest.session.name";
            public static final String DEFAULT_FILE_CHECKSUM_ALGORITHM = "hcjf.net.http.default.file.checksum.algorithm";

            public static final class Folder {
                public static final String FORBIDDEN_CHARACTERS = "hcjf.net.http.folder.forbidden.characters";
                public static final String FILE_EXTENSION_REGEX = "hcjf.net.http.folder.file.extension.regex";
                public static final String DEFAULT_HTML_DOCUMENT = "hcjf.net.http.folder.default.html.document";
                public static final String DEFAULT_HTML_BODY = "hcjf.net.http.folder.default.html.body";
                public static final String DEFAULT_HTML_ROW = "hcjf.net.http.folder.default.html.row";
            }
        }

        public static final class Https {
            public static final String DEFAULT_SERVER_PORT = "hcjf.net.https.default.server.port";
            public static final String DEFAULT_CLIENT_PORT = "hcjf.net.https.default.server.port";
        }

        public static final class Rest {
            public static final String DEFAULT_MIME_TYPE = "hcjf.rest.default.mime.type";
            public static final String DEFAULT_ENCODING_IMPL = "hcjf.rest.default.encoding.impl";
            public static final String QUERY_PATH = "hcjf.rest.query.path";
            public static final String QUERY_PARAMETER_PATH = "hcjf.rest.query.parameter.path";
        }

    }

    public static final class Query {
        public static final String LOG_TAG = "hcjf.query.log.tag";
        public static final String DEFAULT_LIMIT = "hcjf.query.default.limit";
        public static final String DEFAULT_DESC_ORDER = "hcjf.query.default.desc.order";
        public static final String SELECT_REGULAR_EXPRESSION = "hcjf.query.select.regular.expression";
        public static final String CONDITIONAL_REGULAR_EXPRESSION = "hcjf.query.conditional.regular.expression";
        public static final String EVALUATOR_COLLECTION_REGULAR_EXPRESSION = "hcjf.query.evaluator.collection.regular.expression";
        public static final String OPERATION_REGULAR_EXPRESSION = "hcjf.query.operation.regular.expression";
        public static final String JOIN_REGULAR_EXPRESSION = "hcjf.query.join.regular.expression";
        public static final String AS_REGULAR_EXPRESSION = "hcjf.query.as.regular.expression";
        public static final String DESC_REGULAR_EXPRESSION = "hcjf.query.desc.regular.expression";
        public static final String SELECT_GROUP_INDEX = "hcjf.query.select.group.index";
        public static final String FROM_GROUP_INDEX = "hcjf.query.from.group.index";
        public static final String CONDITIONAL_GROUP_INDEX = "hcjf.query.conditional.group.index";
        public static final String JOIN_RESOURCE_NAME_INDEX = "hcjf.query.join.resource.name.index";
        public static final String JOIN_EVALUATORS_INDEX = "hcjf.query.join.evaluators.index";
        public static final String DATE_FORMAT = "hcjf.query.date.format";
        public static final String DECIMAL_SEPARATOR = "hcjf.query.decimal.separator";
        public static final String DECIMAL_FORMAT = "hcjf.query.decimal.format";
        public static final String SCIENTIFIC_NOTATION = "hcjf.query.scientific.notation";
        public static final String SCIENTIFIC_NOTATION_FORMAT = "hcjf.query.scientific.notation.format";
        public static final String EVALUATORS_CACHE_NAME = "hcjf.query.evaluators.cache";

        public static final class ReservedWord {
            public static final String SELECT = "hcjf.query.select.reserved.word";
            public static final String FROM = "hcjf.query.from.reserved.word";
            public static final String JOIN = "hcjf.query.join.reserved.word";
            public static final String INNER = "hcjf.query.inner.join.reserved.word";
            public static final String LEFT = "hcjf.query.left.join.reserved.word";
            public static final String RIGHT = "hcjf.query.right.join.reserved.word";
            public static final String ON = "hcjf.query.on.reserved.word";
            public static final String WHERE = "hcjf.query.where.reserved.word";
            public static final String ORDER_BY = "hcjf.query.order.by.reserved.word";
            public static final String DESC = "hcjf.query.desc.reserved.word";
            public static final String LIMIT = "hcjf.query.limit.reserved.word";
            public static final String START = "hcjf.query.start.reserved.word";
            public static final String RETURN_ALL = "hcjf.query.return.all.reserved.word";
            public static final String ARGUMENT_SEPARATOR = "hcjf.query.argument.separator";
            public static final String EQUALS = "hcjf.query.equals.reserved.word";
            public static final String DISTINCT = "hcjf.query.distinct.reserved.word";
            public static final String DISTINCT_2 = "hcjf.query.distinct.2.reserved.word";
            public static final String GREATER_THAN = "hcjf.query.greater.than.reserved.word";
            public static final String GREATER_THAN_OR_EQUALS = "hcjf.query.greater.than.or.equals.reserved.word";
            public static final String SMALLER_THAN = "hcjf.query.smaller.than.reserved.word";
            public static final String SMALLER_THAN_OR_EQUALS = "hcjf.query.smaller.than.or.equals.reserved.word";
            public static final String IN = "hcjf.query.in.reserved.word";
            public static final String NOT_IN = "hcjf.query.not.in.reserved.word";
            public static final String NOT = "hcjf.query.not.reserved.word";
            public static final String LIKE = "hcjf.query.like.reserved.word";
            public static final String AND = "hcjf.query.and.reserved.word";
            public static final String OR = "hcjf.query.or.reserved.word";
            public static final String STATEMENT_END = "hcjf.query.statement.end.reserved.word";
            public static final String REPLACEABLE_VALUE = "hcjf.query.replaceable.value.reserved.word";
            public static final String STRING_DELIMITER = "hcjf.query.string.delimiter.reserved.word";
            public static final String NULL = "hcjf.query.null.reserved.word";
            public static final String TRUE = "hcjf.query.true.reserved.word";
            public static final String FALSE = "hcjf.query.false.reserved.word";
            public static final String AS = "hcjf.query.as.reserved.word";
            public static final String GROUP_BY = "hcjf.query.group.by.reserved.word";
        }

    }

    public static class Cloud {
        public static final String SERVICE_NAME = "hcjf.cloud.name";
        public static final String SERVICE_PRIORITY = "hcjf.cloud.priority";
        public static final String IMPL = "hcjf.cloud.impl";
        public static final String LOG_TAG = "hcjf.cloud.log.tag";

        public static class TimerTask {
            public static final String MIN_VALUE_OF_DELAY = "hcjf.cloud.timer.task.min.value.of.delay";
            public static final String MAP_NAME = "hcjf.cloud.timer.task.map.name";
            public static final String MAP_SUFFIX_NAME = "hcjf.cloud.timer.task.map.suffix.name";
            public static final String LOCK_SUFFIX_NAME = "hcjf.cloud.timer.task.lock.suffix.name";
            public static final String CONDITION_SUFFIX_NAME = "hcjf.cloud.timer.task.condition.suffix.name";
        }

        public static class Cache {
            public static final String MAP_SUFFIX_NAME = "hcjf.cloud.cache.map.suffix.name";
            public static final String LOCK_SUFFIX_NAME = "hcjf.cloud.cache.lock.suffix.name";
            public static final String CONDITION_SUFFIX_NAME = "hcjf.cloud.cache.condition.suffix.name";
        }
    }

    //Java property names
    public static final String FILE_ENCODING = "file.encoding";

    private static final SystemProperties instance;

    static {
        instance = new SystemProperties();
    }

    private final Map<String, Object> instancesCache;
    private final JsonParser jsonParser;

    private SystemProperties() {
        super(new Properties());
        instancesCache = new HashMap<>();
        jsonParser = new JsonParser();

        defaults.put(HCJF_DEFAULT_DATE_FORMAT, "yyyy-MM-dd HH:mm:ss");
        defaults.put(HCJF_DEFAULT_NUMBER_FORMAT, "0.000");
        defaults.put(HCJF_DEFAULT_DECIMAL_SEPARATOR, ".");
        defaults.put(HCJF_DEFAULT_GROUPING_SEPARATOR, ",");
        defaults.put(HCJF_DEFAULT_LOCALE, Locale.getDefault().toLanguageTag());
        defaults.put(HCJF_DEFAULT_LOCALE_LAYER_IMPLEMENTATION, DefaultLocaleLayer.class.getName());
        defaults.put(HCJF_DEFAULT_LOCALE_LAYER_IMPLEMENTATION_NAME, "default.locale.layer");
        defaults.put(HCJF_DEFAULT_PROPERTIES_FILE_XML, "false");
        defaults.put(HCJF_UUID_REGEX, "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        defaults.put(HCJF_INTEGER_NUMBER_REGEX, "^[-]?[0-9]{1,}$");
        defaults.put(HCJF_DECIMAL_NUMBER_REGEX, "^[-]?[0-9,\\.]{0,}[0-9]{1,}$");
        defaults.put(HCJF_SCIENTIFIC_NUMBER_REGEX, "^[-]?[0-9,\\.]{0,}[0-9]{1,}E[-]?[0-9]{1,}$");

        defaults.put(Layer.LOG_TAG, "LAYER");
        defaults.put(Layer.Deployment.SERVICE_NAME, "DeploymentService");
        defaults.put(Layer.Deployment.SERVICE_PRIORITY, "0");
        defaults.put(Layer.Deployment.CLOUD_DEPLOYMENT_ENABLED, "true");
        defaults.put(Layer.Deployment.CLOUD_DEPLOYMENT_MAP_NAME, "hcjf.layers.plugin.cloud.deployment.map");
        defaults.put(Layer.Deployment.CLOUD_DEPLOYMENT_LOCK_NAME, "hcjf.layers.plugin.cloud.deployment.lock");
        defaults.put(Layer.Deployment.CLOUD_DEPLOYMENT_LOCK_CONDITION_NAME, "hcjf.layers.plugin.cloud.deployment.lock.condition.name");

        defaults.put(Service.STATIC_THREAD_NAME, "StaticServiceThread");
        defaults.put(Service.STATIC_THREAD_POOL_CORE_SIZE, "1");
        defaults.put(Service.STATIC_THREAD_POOL_MAX_SIZE, "5");
        defaults.put(Service.STATIC_THREAD_POOL_KEEP_ALIVE_TIME, "10");
        defaults.put(Service.THREAD_POOL_CORE_SIZE, "100");
        defaults.put(Service.THREAD_POOL_MAX_SIZE, Integer.toString(Integer.MAX_VALUE));
        defaults.put(Service.THREAD_POOL_KEEP_ALIVE_TIME, "10");
        defaults.put(Service.GUEST_SESSION_NAME, "Guest");
        defaults.put(Service.SYSTEM_SESSION_NAME, "System");
        defaults.put(Service.SHUTDOWN_TIME_OUT, "200");

        defaults.put(Event.SERVICE_NAME, "Events");
        defaults.put(Event.SERVICE_PRIORITY, "0");

        defaults.put(Encoding.SERVICE_NAME, "EncodingService");
        defaults.put(Encoding.SERVICE_PRIORITY, "1");

        defaults.put(FileSystem.SERVICE_NAME, "FileSystemWatcherService");
        defaults.put(FileSystem.SERVICE_PRIORITY, "1");
        defaults.put(FileSystem.LOG_TAG, "FILE_SYSTEM_WATCHER_SERVICE");

        defaults.put(Log.SERVICE_NAME, "LogService");
        defaults.put(Log.SERVICE_PRIORITY, "0");
        defaults.put(Log.FILE_PREFIX, "hcfj");
        defaults.put(Log.ERROR_FILE, "false");
        defaults.put(Log.WARNING_FILE, "false");
        defaults.put(Log.INFO_FILE, "false");
        defaults.put(Log.DEBUG_FILE, "false");
        defaults.put(Log.LEVEL, "1");
        defaults.put(Log.DATE_FORMAT, "yyyy-MM-dd HH:mm:ss,SSS");
        defaults.put(Log.CONSUMERS, "[]");
        defaults.put(Log.SYSTEM_OUT_ENABLED, "false");
        defaults.put(Log.QUEUE_INITIAL_SIZE, "10000");
        defaults.put(Log.TRUNCATE_TAG, "false");
        defaults.put(Log.TRUNCATE_TAG_SIZE, "35");
        defaults.put(Log.LOG_CONSUMERS_SIZE, "50");

        defaults.put(Net.INPUT_BUFFER_SIZE, "102400");
        defaults.put(Net.OUTPUT_BUFFER_SIZE, "102400");
        defaults.put(Net.CONNECTION_TIMEOUT_AVAILABLE, "true");
        defaults.put(Net.CONNECTION_TIMEOUT, "30000");
        defaults.put(Net.DISCONNECT_AND_REMOVE, "true");
        defaults.put(Net.WRITE_TIMEOUT, "100");
        defaults.put(Net.IO_THREAD_POOL_KEEP_ALIVE_TIME, "120");
        defaults.put(Net.IO_THREAD_POOL_MAX_SIZE, "10000");
        defaults.put(Net.IO_THREAD_POOL_CORE_SIZE, "100");
        defaults.put(Net.DEFAULT_INPUT_BUFFER_SIZE, "102400");
        defaults.put(Net.DEFAULT_OUTPUT_BUFFER_SIZE, "102400");
        defaults.put(Net.IO_THREAD_DIRECT_ALLOCATE_MEMORY, "false");
        defaults.put(Net.SSL_MAX_IO_THREAD_POOL_SIZE, "2");

        defaults.put(Net.Ssl.DEFAULT_KEY_PASSWORD, "hcjfkeypassword");
        defaults.put(Net.Ssl.DEFAULT_KEY_TYPE, "JKS");
        defaults.put(Net.Ssl.DEFAULT_KEYSTORE_PASSWORD, "hcjfkeystorepassword");
        defaults.put(Net.Ssl.DEFAULT_KEYSTORE_FILE_PATH, "./src/resources/org/hcjf/io/net/https/keystore.jks");
        defaults.put(Net.Ssl.DEFAULT_TRUSTED_CERTS_FILE_PATH, "./src/resources/org/hcjf/io/net/https/cacerts.jks");
        defaults.put(Net.Ssl.DEFAULT_PROTOCOL, "TLSv1.2");
        defaults.put(Net.Ssl.IO_THREAD_NAME, "SslIoThread");
        defaults.put(Net.Ssl.ENGINE_THREAD_NAME, "SslEngineThread");

        defaults.put(Net.Http.SERVER_NAME, "HCJF Web Server");
        defaults.put(Net.Http.RESPONSE_DATE_HEADER_FORMAT_VALUE, "EEE, dd MMM yyyy HH:mm:ss z");
        defaults.put(Net.Http.INPUT_LOG_BODY_MAX_LENGTH, "128");
        defaults.put(Net.Http.OUTPUT_LOG_BODY_MAX_LENGTH, "128");
        defaults.put(Net.Http.DEFAULT_SERVER_PORT, "80");
        defaults.put(Net.Http.DEFAULT_CLIENT_PORT, "80");
        defaults.put(Net.Http.STREAMING_LIMIT_FILE_SIZE, "10240");
        defaults.put(Net.Http.DEFAULT_ERROR_FORMAT_SHOW_STACK, "true");
        defaults.put(Net.Http.DEFAULT_CLIENT_CONNECT_TIMEOUT, "10000");
        defaults.put(Net.Http.DEFAULT_CLIENT_READ_TIMEOUT, "10000");
        defaults.put(Net.Http.DEFAULT_CLIENT_WRITE_TIMEOUT, "10000");
        defaults.put(Net.Http.DEFAULT_GUEST_SESSION_NAME, "Http guest session");
        defaults.put(Net.Http.DEFAULT_FILE_CHECKSUM_ALGORITHM, "MD5");

        defaults.put(Net.Http.Folder.FORBIDDEN_CHARACTERS, "[]");
        defaults.put(Net.Http.Folder.FILE_EXTENSION_REGEX, "\\.(?=[^\\.]+$)");
        defaults.put(Net.Http.Folder.DEFAULT_HTML_DOCUMENT, "<!DOCTYPE html><html><head><title>%s</title><body>%s</body></html></head>");
        defaults.put(Net.Http.Folder.DEFAULT_HTML_BODY, "<table>%s</table>");
        defaults.put(Net.Http.Folder.DEFAULT_HTML_ROW, "<tr><th><a href=\"%s\">%s</a></th></tr>");

        defaults.put(Net.Https.DEFAULT_SERVER_PORT, "443");
        defaults.put(Net.Https.DEFAULT_CLIENT_PORT, "443");

        defaults.put(Net.Rest.DEFAULT_MIME_TYPE, "application/json");
        defaults.put(Net.Rest.DEFAULT_ENCODING_IMPL, "hcjf");
        defaults.put(Net.Rest.QUERY_PATH, "query");
        defaults.put(Net.Rest.QUERY_PARAMETER_PATH, "q");

        defaults.put(Query.LOG_TAG, "QUERY");
        defaults.put(Query.DEFAULT_LIMIT, "1000");
        defaults.put(Query.DEFAULT_DESC_ORDER, "false");
        defaults.put(Query.SELECT_REGULAR_EXPRESSION, "(?i)^(select[  ]{1,}[a-zA-Z_0-9,.*\\$ ]{1,})([  ]?from[  ]{1,}[a-zA-Z_0-9.]{1,}[  ]?)([a-zA-Z_0-9'=,.* ?%\\$<>!\\:\\-()\\[\\]]{1,})?[$;]?");
        defaults.put(Query.CONDITIONAL_REGULAR_EXPRESSION, "(?i)((?<=(^((inner |left |right )?join )|^where |^limit |^start |^order by |^group by |(( inner | left | right )? join )| where | limit | start | order by | group by )))|(?=(^((inner |left |right )?join )|^where |^limit |^start |^order by |^group by |(( inner | left | right )? join )| where | limit | start | order by | group by ))");
        defaults.put(Query.EVALUATOR_COLLECTION_REGULAR_EXPRESSION, "(?i)((?<=( and | or ))|(?=( and | or )))");
        defaults.put(Query.OPERATION_REGULAR_EXPRESSION, "(?i)(?<=(=|<>|!=|>|<|>=|<=| in | not in | like ))|(?=(=|<>|!=|>|<|>=|<=| in | not in | like ))");
        defaults.put(Query.JOIN_REGULAR_EXPRESSION, "(?i)( on )");
        defaults.put(Query.AS_REGULAR_EXPRESSION, "(?i)((?<=( as ))|(?=( as )))");
        defaults.put(Query.DESC_REGULAR_EXPRESSION, "(?i)( desc )");
        defaults.put(Query.SELECT_GROUP_INDEX, "1");
        defaults.put(Query.FROM_GROUP_INDEX, "2");
        defaults.put(Query.CONDITIONAL_GROUP_INDEX, "3");
        defaults.put(Query.JOIN_RESOURCE_NAME_INDEX, "0");
        defaults.put(Query.JOIN_EVALUATORS_INDEX, "1");
        defaults.put(Query.DATE_FORMAT, "yyyy-MM-dd HH:mm:ss");
        defaults.put(Query.DECIMAL_SEPARATOR, ".");
        defaults.put(Query.DECIMAL_FORMAT, "0.000");
        defaults.put(Query.SCIENTIFIC_NOTATION, "E");
        defaults.put(Query.SCIENTIFIC_NOTATION_FORMAT, "0.0E0");
        defaults.put(Query.EVALUATORS_CACHE_NAME, "__evaluators__cache__");
        defaults.put(Query.ReservedWord.SELECT, "SELECT");
        defaults.put(Query.ReservedWord.FROM, "FROM");
        defaults.put(Query.ReservedWord.JOIN, "JOIN");
        defaults.put(Query.ReservedWord.INNER, "INNER");
        defaults.put(Query.ReservedWord.LEFT, "LEFT");
        defaults.put(Query.ReservedWord.RIGHT, "RIGHT");
        defaults.put(Query.ReservedWord.ON, "ON");
        defaults.put(Query.ReservedWord.WHERE, "WHERE");
        defaults.put(Query.ReservedWord.ORDER_BY, "ORDER BY");
        defaults.put(Query.ReservedWord.DESC, "DESC");
        defaults.put(Query.ReservedWord.LIMIT, "LIMIT");
        defaults.put(Query.ReservedWord.START, "START");
        defaults.put(Query.ReservedWord.RETURN_ALL, "*");
        defaults.put(Query.ReservedWord.ARGUMENT_SEPARATOR, ",");
        defaults.put(Query.ReservedWord.EQUALS, "=");
        defaults.put(Query.ReservedWord.DISTINCT, "<>");
        defaults.put(Query.ReservedWord.DISTINCT_2, "!=");
        defaults.put(Query.ReservedWord.GREATER_THAN, ">");
        defaults.put(Query.ReservedWord.GREATER_THAN_OR_EQUALS, ">=");
        defaults.put(Query.ReservedWord.SMALLER_THAN, "<");
        defaults.put(Query.ReservedWord.SMALLER_THAN_OR_EQUALS, "<=");
        defaults.put(Query.ReservedWord.IN, "IN");
        defaults.put(Query.ReservedWord.NOT_IN, "NOT IN");
        defaults.put(Query.ReservedWord.NOT, "NOT");
        defaults.put(Query.ReservedWord.LIKE, "LIKE");
        defaults.put(Query.ReservedWord.AND, "AND");
        defaults.put(Query.ReservedWord.OR, "OR");
        defaults.put(Query.ReservedWord.STATEMENT_END, ";");
        defaults.put(Query.ReservedWord.REPLACEABLE_VALUE, "?");
        defaults.put(Query.ReservedWord.STRING_DELIMITER, "'");
        defaults.put(Query.ReservedWord.NULL, "NULL");
        defaults.put(Query.ReservedWord.TRUE, "TRUE");
        defaults.put(Query.ReservedWord.FALSE, "FALSE");
        defaults.put(Query.ReservedWord.AS, "AS");
        defaults.put(Query.ReservedWord.GROUP_BY, "GROUP BY");

        defaults.put(Cloud.SERVICE_NAME, "CloudService");
        defaults.put(Cloud.SERVICE_PRIORITY, "0");
        defaults.put(Cloud.IMPL, "");
        defaults.put(Cloud.LOG_TAG, "CLOUD");
        defaults.put(Cloud.TimerTask.MIN_VALUE_OF_DELAY, "30000");
        defaults.put(Cloud.TimerTask.MAP_NAME, "hcjf.cloud.timer.task.map");
        defaults.put(Cloud.TimerTask.MAP_SUFFIX_NAME, "hcjf.cloud.timer.task.map.");
        defaults.put(Cloud.TimerTask.LOCK_SUFFIX_NAME, "hcjf.cloud.timer.task.lock.");
        defaults.put(Cloud.TimerTask.CONDITION_SUFFIX_NAME, "hcjf.cloud.timer.task.condition.");
        defaults.put(Cloud.Cache.MAP_SUFFIX_NAME, "hcjf.cloud.cache.map.");
        defaults.put(Cloud.Cache.LOCK_SUFFIX_NAME, "hcjf.cloud.cache.lock.");
        defaults.put(Cloud.Cache.CONDITION_SUFFIX_NAME, "hcjf.cloud.cache.condition.");

        Properties system = System.getProperties();
        putAll(system);
        System.setProperties(this);
    }

    /**
     * Put the default value for a property.
     * @param propertyName Property name.
     * @param defaultValue Property default value.
     * @throws NullPointerException Throw a {@link NullPointerException} when the
     * property name or default value are null.
     */
    public static void putDefaultValue(String propertyName, String defaultValue) {
        if(propertyName == null) {
            throw new NullPointerException("Invalid property name null");
        }

        if(defaultValue == null) {
            throw new NullPointerException("Invalid default value null");
        }

        instance.defaults.put(propertyName, defaultValue);
    }

    /**
     * Calls the <tt>Hashtable</tt> method {@code put}. Provided for
     * parallelism with the <tt>getProperty</tt> method. Enforces use of
     * strings for property keys and values. The value returned is the
     * result of the <tt>Hashtable</tt> call to {@code put}.
     *
     * @param key   the key to be placed into this property list.
     * @param value the value corresponding to <tt>key</tt>.
     * @return the previous value of the specified key in this property
     * list, or {@code null} if it did not have one.
     * @see #getProperty
     * @since 1.2
     */
    @Override
    public synchronized Object setProperty(String key, String value) {
        Object result = super.setProperty(key, value);

        synchronized (instancesCache) {
            instancesCache.remove(key);
        }

        try {
            //TODO: Create listeners
        } catch (Exception ex){}

        return result;
    }

    /**
     * This method return the string value of the system property
     * named like the parameter.
     * @param propertyName Name of the find property.
     * @param validator Property validator.
     * @return Return the value of the property or null if the property is no defined.
     */
    public static String get(String propertyName, PropertyValueValidator<String> validator) {
        String result = System.getProperty(propertyName);

        if(result == null) {
            org.hcjf.log.Log.d("Property not found: $1",  propertyName);
        }

        if(validator != null) {
            if(!validator.validate(result)){
                throw new IllegalPropertyValueException(propertyName + "=" + result);
            }
        }

        return result;
    }

    /**
     * This method return the string value of the system property
     * named like the parameter.
     * @param propertyName Name of the find property.
     * @return Return the value of the property or null if the property is no defined.
     */
    public static String get(String propertyName) {
        return get(propertyName, null);
    }

    /**
     * This method return the value of the system property as boolean.
     * @param propertyName Name of the find property.
     * @return Value of the system property as boolean, or null if the property is not found.
     */
    public static Boolean getBoolean(String propertyName) {
        Boolean result = null;

        synchronized (instance.instancesCache) {
            result = (Boolean) instance.instancesCache.get(propertyName);
            if (result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = Boolean.valueOf(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a boolean valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as integer.
     * @param propertyName Name of the find property.
     * @return Value of the system property as integer, or null if the property is not found.
     */
    public static Integer getInteger(String propertyName) {
        Integer result = null;

        synchronized (instance.instancesCache) {
            result = (Integer) instance.instancesCache.get(propertyName);
            if (result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = Integer.decode(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a integer valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as long.
     * @param propertyName Name of the find property.
     * @return Value of the system property as long, or null if the property is not found.
     */
    public static Long getLong(String propertyName) {
        Long result = null;

        synchronized (instance.instancesCache) {
            result = (Long) instance.instancesCache.get(propertyName);
            if (result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = Long.decode(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a long valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as double.
     * @param propertyName Name of the find property.
     * @return Value of the system property as double, or null if the property is not found.
     */
    public static Double getDouble(String propertyName) {
        Double result = null;

        synchronized (instance.instancesCache) {
            result = (Double) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = Double.valueOf(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a double valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as {@link UUID} instance.
     * @param propertyName Name of the find property.
     * @return Value of the system property as {@link UUID} instance, or null if the property is not found.
     */
    public static UUID getUUID(String propertyName) {
        UUID result = null;

        synchronized (instance.instancesCache) {
            result = (UUID) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = UUID.fromString(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a UUID valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as {@link Path} instance.
     * @param propertyName Name of the find property.
     * @return Value of the system property as {@link Path} instance, or null if the property is not found.
     */
    public static Path getPath(String propertyName) {
        Path result = null;

        synchronized (instance.instancesCache) {
            result = (Path) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    if (propertyValue != null) {
                        result = Paths.get(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a path valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * This method return the value of the system property as class.
     * @param propertyName Name of the find property.
     * @param <O> Type of the class instance expected.
     * @return Class instance.
     */
    public static <O extends Object> Class<O> getClass(String propertyName) {
        Class<O> result;

        synchronized (instance.instancesCache) {
            result = (Class<O>) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    if(propertyValue != null) {
                        result = (Class<O>) Class.forName(propertyValue);
                        instance.instancesCache.put(propertyName, result);
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a class name valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }

        return result;
    }

    /**
     * Return the default charset of the JVM instance.
     * @return Default charset.
     */
    public static String getDefaultCharset() {
        return System.getProperty(FILE_ENCODING);
    }

    /**
     * This method return the value of the property as Locale instance.
     * The instance returned will be stored on near cache and will be removed when the
     * value of the property has been updated.
     * @param propertyName Name of the property that contains locale representation.
     * @return Locale instance.
     */
    public static Locale getLocale(String propertyName) {
        Locale result;
        synchronized (instance.instancesCache) {
            result = (Locale) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    result = Locale.forLanguageTag(propertyValue);
                    instance.instancesCache.put(propertyName, result);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a locale tag valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    /**
     * This method return the valuo of the property called 'hcjf.default.locale' as a locale instance.
     * The instance returned will be stored on near cache and will be removed when the
     * value of the property has been updated.
     * @return Locale instance.
     */
    public static Locale getLocale() {
        return getLocale(HCJF_DEFAULT_LOCALE);
    }

    /**
     * This method return the value of the property as a DecimalFormat instnace.
     * The instance returned will be stored on near cache and will be removed when the
     * value of the property has been updated.
     * @param propertyName Name of the property that contains decimal pattern.
     * @return DecimalFormat instance.
     */
    public static DecimalFormat getDecimalFormat(String propertyName) {
        DecimalFormat result;
        synchronized (instance.instancesCache) {
            result = (DecimalFormat) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(get(HCJF_DEFAULT_DECIMAL_SEPARATOR).charAt(0));
                    symbols.setGroupingSeparator(get(HCJF_DEFAULT_GROUPING_SEPARATOR).charAt(0));
                    result = new DecimalFormat(propertyValue, symbols);
                    instance.instancesCache.put(propertyName, result);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a decimal pattern valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    /**
     * This method return the value of the property as a SimpleDateFormat instance.
     * The instance returned will be stored on near cache and will be removed when the
     * value of the property has been updated.
     * @param propertyName Name of the property that contains date representation.
     * @return Simple date format instance.
     */
    public static SimpleDateFormat getDateFormat(String propertyName) {
        SimpleDateFormat result;
        synchronized (instance.instancesCache) {
            result = (SimpleDateFormat) instance.instancesCache.get(propertyName);
            if(result == null) {
                String propertyValue = get(propertyName);
                try {
                    result = new SimpleDateFormat(get(propertyName));
                    instance.instancesCache.put(propertyName, result);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a date pattern valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    /**
     * This method return the value of the property as instance of list.
     * @param propertyName Name of the property that contains the json array representation.
     * @return List instance.
     */
    public static List<String> getList(String propertyName) {
        String propertyValue = get(propertyName);
        List<String> result = new ArrayList<>();
        synchronized (instance.instancesCache) {
            if (instance.instancesCache.containsKey(propertyName)) {
                result.addAll((List<? extends String>) instance.instancesCache.get(propertyName));
            } else {
                try {
                    JsonArray array = (JsonArray) instance.jsonParser.parse(propertyValue);
                    array.forEach(A -> result.add(A.getAsString()));
                    List<String> cachedResult = new ArrayList<>();
                    cachedResult.addAll(result);
                    instance.instancesCache.put(propertyName, cachedResult);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a json array valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    /**
     * This method return the value of the property as instance of set.
     * @param propertyName Name of the property that contains the json array representation.
     * @return Set instance.
     */
    public static Set<String> getSet(String propertyName) {
        String propertyValue = get(propertyName);
        Set<String> result = new TreeSet<>();
        synchronized (instance.instancesCache) {
            if (instance.instancesCache.containsKey(propertyName)) {
                result.addAll((List<? extends String>) instance.instancesCache.get(propertyName));
            } else {
                try {
                    JsonArray array = (JsonArray) instance.jsonParser.parse(propertyValue);
                    array.forEach(A -> result.add(A.getAsString()));
                    List<String> cachedResult = new ArrayList<>();
                    cachedResult.addAll(result);
                    instance.instancesCache.put(propertyName, cachedResult);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a json array valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    /**
     * This method return the value of the property as instance of map.
     * @param propertyName The name of the property that contains the json object representation.
     * @return Map instance.
     */
    public static Map<String, String> getMap(String propertyName) {
        String propertyValue = get(propertyName);
        Map<String, String> result = new HashMap<>();
        synchronized (instance.instancesCache) {
            if (instance.instancesCache.containsKey(propertyName)) {
                result.putAll((Map<String, String>) instance.instancesCache.get(propertyName));
            } else {
                try {
                    JsonObject object = (JsonObject) instance.jsonParser.parse(propertyValue);
                    object.entrySet().forEach(S -> result.put(S.getKey(), object.get(S.getKey()).getAsString()));
                    Map<String, String> cachedResult = new HashMap<>();
                    cachedResult.putAll(result);
                    instance.instancesCache.put(propertyName, cachedResult);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a json object valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

    public static Pattern getPattern(String propertyName) {
        return getPattern(propertyName, 0);
    }

    /**
     * Return the compiled pattern from the property value.
     * @param propertyName Name of the property.
     * @param flags Regex flags.
     * @return Compiled pattern.
     */
    public static Pattern getPattern(String propertyName, int flags) {
        String propertyValue = get(propertyName);
        Pattern result;
        synchronized (instance.instancesCache) {
            if(instance.instancesCache.containsKey(propertyName)) {
                result = (Pattern) instance.instancesCache.get(propertyName);
            } else {
                try {
                    result = Pattern.compile(propertyValue, flags);
                    instance.instancesCache.put(propertyName, result);
                } catch (Exception ex) {
                    throw new IllegalArgumentException("The property value has not a regex valid format: '"
                            + propertyName + ":" + propertyValue + "'", ex);
                }
            }
        }
        return result;
    }

}
