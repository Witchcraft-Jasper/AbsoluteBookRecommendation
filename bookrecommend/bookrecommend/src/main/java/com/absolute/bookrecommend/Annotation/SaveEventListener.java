package com.absolute.bookrecommend.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.absolute.bookrecommend.POJO.IncInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {
    private static final Logger logger = LoggerFactory.getLogger(SaveEventListener.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        logger.info(event.getSource().toString());
        final Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                ReflectionUtils.makeAccessible(field);
                // 如果字段添加了我们自定义的AutoIncKey注解
                if (field.isAnnotationPresent(AutoIncKey.class)) {
                    // 设置自增ID
                    field.set(source, getNextId(source.getClass().getSimpleName()));
                }
            });
        }

    }

    private Integer getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("incId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        IncInfo inc = mongoTemplate.findAndModify(query, update, options, IncInfo.class);
        assert inc != null;
        return inc.getIncId();
    }
}