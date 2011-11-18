package dk.redhat.demo;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("blogManager")
public class BlogManager {

	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Blog> getBlogs() {
		Query blogQuery = entityManager.createQuery("select b from Blog b");
		return (List<Blog>) blogQuery.getResultList();
	}
	
	public Blog getBlog(long id) {
		return entityManager.find(Blog.class, id);
	}
	
	public void addEntry(long blogid, String title, String body) {
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle(title);
		blogPost.setBody(body);
		blogPost.setBlog(entityManager.find(Blog.class, blogid));
		entityManager.persist(blogPost);
	}
	
}
