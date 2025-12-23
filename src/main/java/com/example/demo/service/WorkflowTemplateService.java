@Service
public class WorkflowTemplateService {

    @Autowired
    private WorkflowTemplateRepository repository;

    public WorkflowTemplate create(WorkflowTemplate template) {
        return repository.save(template);
    }

    public WorkflowTemplate getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<WorkflowTemplate> getAll() {
        return repository.findAll();
    }

    public WorkflowTemplate setActive(Long id, boolean active) {
        WorkflowTemplate t = getById(id);
        if (t != null) {
            t.setActive(active);
            return repository.save(t);
        }
        return null;
    }
}
