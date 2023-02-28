package Tuhoc.example.demo.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Tuhoc.example.demo.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{
	@Modifying
	@Query("delete from UserRole ur where ur.user.id = :uid")
	public void deleteByUserId(@Param("uid") int x);
	@Query("SELECT u FROM UserRole u where u.role = s")
	Page<UserRole>searchByRole(@Param("s") String s,Pageable pageable);
}
