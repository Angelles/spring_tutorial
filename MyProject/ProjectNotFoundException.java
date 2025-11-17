package MyProject;

class ProjectNotFoundException extends RuntimeException {
    /* Exception thrown if project is not in database/not found */
        ProjectNotFoundException(Integer id) {
            super("Could not find project " + id);
        }
}
