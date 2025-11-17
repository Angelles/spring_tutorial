package MyProject;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

public class ProjectController {
    private final ProjectRepository repository;
    private final ProjectModelAssembler assembler;


    ProjectController(ProjectRepository repository, ProjectModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    /* Get project by id */
    @GetMapping("/projects/{id}")
    EntityModel<Project> one(@PathVariable Integer id) {

        Project project = repository.findById(id) //
                .orElseThrow(() -> new ProjectNotFoundException(id));

        /* old code - creating entity model instance of the project
        return EntityModel.of(project, //
                linkTo(methodOn(ProjectController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ProjectController.class).all()).withRel("projects"));
                */
         return assembler.toModel(project); // uses project model assembler
    }

    /* Get all projects */
    @GetMapping("/projects")
    CollectionModel<EntityModel<Project>> all() {

        List<EntityModel<Project>> projects = repository.findAll().stream()
                /* .map(project -> EntityModel.of(project,
                        linkTo(methodOn(ProjectController.class).one(project.getId())).withSelfRel(),
                        linkTo(methodOn(ProjectController.class).all()).withRel("projects")))*/
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(projects, linkTo(methodOn(ProjectController.class).all()).withSelfRel());
    }

    /* Create new project */
    @PostMapping("/projects")
    ResponseEntity<?> newProject(@RequestBody Project newProject) {

        EntityModel<Project> entityModel = assembler.toModel(repository.save(newProject));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    /* Update project (all fields) */
    @PutMapping("/projects/{id}")
    ResponseEntity<?> replaceProject(@RequestBody Project newProject, @PathVariable Integer id) {
        Project updatedProject = repository.findById(id) //
                .map(project -> {
                    project.setName(newProject.getName());
                    project.setCraft(newProject.getCraft());
                    return repository.save(project);
                }) //
                .orElseGet(() -> {
                    return repository.save(newProject);
                });

        EntityModel<Project> entityModel = assembler.toModel(updatedProject);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    
    /* Delete project */
    @DeleteMapping("/projects/{id}")
    ResponseEntity<?> deleteProject(@PathVariable Integer id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
