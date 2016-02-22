package com.example.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.entity.Member;

/**
 * @author Ravisankar C
 *
 */
@Stateless
public class MemberRespository {

	@PersistenceContext(unitName="tomeedemo")
	private EntityManager em;
	
	public Member addMember(Member m) {
		em.persist(m);
		return m;
	}
	
	public Member findMember(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
	}
	
}
