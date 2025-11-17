package MyProject;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProjectRepository extends JpaRepository<Project, Integer> {
}