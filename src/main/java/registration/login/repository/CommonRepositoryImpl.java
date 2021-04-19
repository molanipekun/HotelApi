//package registration.login.repository;
//
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//import registration.login.model.AbstractEntity;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//@Transactional
//public class CommonRepositoryImpl<T extends AbstractEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {
//    private final JpaEntityInformation<T, ?> entityInformation;
//    private final EntityManager em;
//    private final Class<T> domainClass;
//
//
//
//    public CommonRepositoryImpl(Class<T> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.em = em;
//        this.domainClass = domainClass;
//        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
//    }
//
//
////    <S extends T> S save(S var1);
////
////    <S extends T> Iterable<S> save(Iterable<S> var1);
//
////    public <T extends SerializableEntity<T>> String makerCheckerUpdate(T entity) throws JsonProcessingException, VerificationException {
////        String message = verificationService.addNewVerificationRequest(entity);
////        return message;
////    }
//
//
//    @Override
//    @Transactional
//    public void delete(ID id) {
//        T t = getOne(id);
//        t.setDelFlag("Y");
//        t.setDeletedOn(new Date());
//
//        super.save(t);
//    }
//
//    @Override
//    @Transactional
//    public void delete(T entity) {
//
//        entity.setDelFlag("Y");
//        entity.setDeletedOn(new Date());
//        super.save(entity);
//    }
//
//    @Override
//    @Transactional
//    public void delete(Iterable<? extends T> entities) {
//        Assert.notNull(entities, "The given Iterable of entities can  not be null!");
//        Iterator<? extends T> var2 = entities.iterator();
//
//        while (var2.hasNext()) {
//            T entity = var2.next();
//            entity.setDelFlag("Y");
//            entity.setDeletedOn(new Date());
//            super.save(entity);
//        }
//
//    }
//
//
//    @Override
//    @Transactional
//    public void deleteAll()
//    {
//        Iterator<T> var1 = this.findAll().iterator();
//
//        while (var1.hasNext()) {
//            T entity = var1.next();
//            entity.setDelFlag("Y");
//            entity.setDeletedOn(new Date());
//            super.save(entity);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void deleteAllInBatch() {
////        super.deleteAllInBatch();
//    }
//
//
//
//    @Override
//    public Page<T> findUsingPattern(String pattern, Pageable details)
//    {
////        String lpattern = pattern.toLowerCase();
////        CriteriaBuilder cb = em.getCriteriaBuilder();
////        CriteriaQuery<T> q = cb.createQuery(entityInformation.getJavaType());
////        Root<T> c = q.from(entityInformation.getJavaType());
////        Predicate[] predicates = null;
////        try {
////            predicates = new Predicate[getFields().size()];
////            int cnt = 0;
////            for (String field : getFields()) {
////                Predicate predicate = cb.like(cb.lower(c.get(field)), "%" + lpattern + "%");
////                predicates[cnt] = predicate;
////                cnt++;
////            }
////        }
////        catch (InstantiationException | IllegalAccessException e)
////        {
////            return new PageImpl<>(new ArrayList<>());
////        }
////
////        CriteriaQuery<T> baseQuery = null;
////        CriteriaQuery<Long> qc = cb.createQuery(Long.class);
////        CriteriaQuery<Long> countQuery = null;
////        if(predicates.length > 0)
////        {
////            Predicate or = cb.or (predicates);
////            baseQuery = q.select(c).where(or);
////            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType()))).where(or);
////        }
////        else
////        {
////            baseQuery = q.select(c);
////            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType())));
////        }
////
////        TypedQuery<T> query = em.createQuery(baseQuery);
////        Long count = em.createQuery(countQuery).getSingleResult();
////        query.setFirstResult(details.getOffset());
////        query.setMaxResults(details.getPageSize());
////        List<T> list = query.getResultList();
////        return new PageImpl<T>(list, details, count);
//
//        String lpattern = pattern.toLowerCase();
//        System.out.println (lpattern + "pattern to be searched ");
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> q = cb.createQuery(entityInformation.getJavaType());
//        Root<T> c = q.from(entityInformation.getJavaType());
//        Predicate[] predicates = null;
//        try {
//            predicates = new Predicate[getFields().size()];
//            int cnt = 0;
//            for (String field : getFields()) {
//                Predicate predicate = cb.like(cb.lower(c.get(field)), "%" + lpattern + "%");
//                predicates[cnt] = predicate;
//                cnt++;
//            }
//        }
//        catch (InstantiationException | IllegalAccessException e)
//        {
//            return new PageImpl<>(new ArrayList<>());
//        }
//
//        CriteriaQuery<T> baseQuery = null;
//        CriteriaQuery<Long> qc = cb.createQuery(Long.class);
//        CriteriaQuery<Long> countQuery = null;
//        if(predicates.length > 0)
//        {
//            Predicate or = cb.or (predicates);
//            baseQuery = q.select(c).where(or);
//            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType()))).where(or);
//        }
//        else
//        {
//            baseQuery = q.select(c);
//            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType())));
//        }
//        System.out.println ("base query is " + baseQuery.getParameters ());
//        TypedQuery<T> query = em.createQuery(baseQuery);
//        Long count = em.createQuery(countQuery).getSingleResult();
//        query.setFirstResult((int) details.getOffset());
//        query.setMaxResults(details.getPageSize());
//        List<T> list = query.getResultList();
//        System.out.println("the list returned {}"+list.size());
//
//        return new PageImpl<T>(list, details, count);
//    }
//
//
//
////    private List<String> getFields() throws InstantiationException, IllegalAccessException
////    {
////        Class<T> type = entityInformation.getJavaType();
////        AbstractEntity en = (AbstractEntity) type.newInstance();
////        return en.getDefaultSearchFields();
////
////    }
//
//    private List<String> getFields() throws InstantiationException, IllegalAccessException
//    {
//        Class<T> type = entityInformation.getJavaType();
//        List<String> fields  = new ArrayList<> (  );
//        for ( Field f:type.getDeclaredFields ()) {
//            if(f.getType ().equals ( String.class ) ) {
//                fields.add ( f.getName () );
//            }
//        }
//        Class t = type.getSuperclass () ;
//        if(t != null){
//            for ( Field f:t.getDeclaredFields ()) {
//                if(f.getType ().equals ( String.class ) ) {
//                    fields.add ( f.getName () );
//                }
//            }
//
//        }
//
//        System.out.println ("fields size is "+ fields.size ());
//        AbstractEntity en = (AbstractEntity)type.newInstance();
//
//        return fields;
//
//    }
//
//
//
//
//}
