package com.lambo.onlineretailers.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @ClassName: SpecificationHelper
 * @Author: yym
 * @Description: ${description}
 * @Date: 2019/12/27 13:49
 * @Version: 1.0
 */
public class SpecificationHelper {
    public static <ATTR, E> Path<ATTR> getPath(Root<E> root, String attrName) {
        From<E, ?> f = root;
        String[] strs = attrName.split("\\.");
        String attr = attrName;
        if (strs.length > 1) {
            for(int i = 0; i < strs.length; i ++) {
                attr = strs[i];
                if(i < strs.length - 1) {
                    boolean hasAttribute = false;
                    if (root.getJoins() != null) {
                        for (Join<E, ?> join : root.getJoins()) {
                            if (attr.equals(join.getAttribute().getName())) {
                                f = join;
                                hasAttribute = true;
                                break;
                            }
                        }
                    }
                    if(!hasAttribute) {
                        f = f.join(attr);
                    }
                }
            }
        }
        return f.get(attr);
    }

    public static <E> From<E, ?> fromCollection(Root<E> root, String attrName) {
        From<E, ?> f = root;
        String attr = attrName;
        StringTokenizer tokenizer = new StringTokenizer(attrName, ".");
        while (tokenizer.hasMoreTokens()) {
            attr = tokenizer.nextToken();
            boolean hasAttribute = false;
            if (root.getJoins() != null) {
                for (Join<E, ?> join : root.getJoins()) {
                    if (attr.equals(join.getAttribute().getName())) {
                        f = join;
                        hasAttribute = true;
                        break;
                    }
                }
            }
            if(!hasAttribute) {
                f = f.join(attr);
            }
        }
        return f;
    }

    public static <T> Specification<T> and(List<Specification<T>> specList) {
        Specification<T> specs = null;
        for(Specification<T> s : specList) {
            if(specs != null) {
                specs = specs.and(s);
            } else {
                specs = Specification.where(s);
            }
        }
        return specs;
    }

    public static <T> Specification<T> or(List<Specification<T>> specList) {
        Specification<T> specs = null;
        for(Specification<T> s : specList) {
            if(specs != null) {
                specs = specs.or(s);
            } else {
                specs = Specification.where(s);
            }
        }
        return specs;
    }
}
