package cz.zcu.kiv.eegdatabase.data.persistent;

import cz.zcu.kiv.eegdatabase.data.nosql.entities.ExperimentElastic;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.PostLoadEventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by stebjan on 2.12.2015.
 */
public class PostLoadListener implements PostLoadEventListener {

    protected Log log = LogFactory.getLog(getClass());

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void onPostLoad(PostLoadEvent event) {
        Object obj = event.getEntity();
        if (obj instanceof Experiment) {
            Experiment e = (Experiment) obj;
            if (e.getDataFiles().size() > 0) {
                SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new IdsQueryBuilder("experiment").addIds("" + e.getExperimentId())).build();
                List<ExperimentElastic> elastic = elasticsearchTemplate.queryForList(searchQuery, ExperimentElastic.class);
                if (elastic.size() > 0 && elastic.get(0) != null) {
                    e.setElasticExperiment(elastic.get(0));
                }
            }
        }
    }
}