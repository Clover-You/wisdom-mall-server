package top.ctong.wisdom.core.converter.doc;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 *
 * </p>
 *
 * @author Clover
 * @date 2023-07-27 16:43
 */
@Component
public class LongToStringModelConverter implements ModelConverter {

    private final ObjectMapperProvider objectMapperProvider;

    public LongToStringModelConverter(ObjectMapperProvider objectMapperProvider) {
        this.objectMapperProvider = objectMapperProvider;
    }

    /**
     * 将 Springdoc 中的 Long 类型转为 String
     *
     * @return Schema<?>
     * @author Clover You
     * @date 2023/7/27 17:11
     */
    @Override
    public Schema<?> resolve(
        AnnotatedType type, ModelConverterContext modelConverterContext, Iterator<ModelConverter> iterator
    ) {
        var javaType = objectMapperProvider.jsonMapper().constructType(type.getType());
        var rawClass = javaType.getRawClass();

        // 如果是 Long 类型，那么一律转为 String
        if (rawClass.equals(Long.class)) {
            type.setType(String.class);
            return iterator.next().resolve(type, modelConverterContext, iterator);
        }
        return iterator.hasNext() ? iterator.next().resolve(type, modelConverterContext, iterator) : null;
    }
}
