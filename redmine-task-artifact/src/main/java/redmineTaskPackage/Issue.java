package redmineTaskPackage;

public class Issue {
    public float id;
    public Project project;
    public Tracker tracker;
    public Status status;
    public Priority priority;
    public Author author;
    public Assigned_to assigned_to;
    public Parent parent;
    public String subject;
    public String description;
    public String start_date;
    public String due_date;
    public float done_ratio;
    public boolean is_private;
    public float estimated_hours;
    public String created_on;
    public String updated_on;
    public String closed_on = null;


    // Getter Methods 

    public float getId() {
     return id;
    }

    public Project getProject() {
     return project;
    }

    public Tracker getTracker() {
     return tracker;
    }

    public Status getStatus() {
     return status;
    }

    public Priority getPriority() {
     return priority;
    }

    public Author getAuthor() {
     return author;
    }

    public Assigned_to getAssigned_to() {
     return assigned_to;
    }

    public Parent getParent() {
     return parent;
    }

    public String getSubject() {
     return subject;
    }

    public String getDescription() {
     return description;
    }

    public String getStart_date() {
     return start_date;
    }

    public String getDue_date() {
     return due_date;
    }

    public float getDone_ratio() {
     return done_ratio;
    }

    public boolean getIs_private() {
     return is_private;
    }

    public float getEstimated_hours() {
     return estimated_hours;
    }

    public String getCreated_on() {
     return created_on;
    }

    public String getUpdated_on() {
     return updated_on;
    }

    public String getClosed_on() {
     return closed_on;
    }

    // Setter Methods 

    public void setId(float id) {
     this.id = id;
    }

    public void setProject(Project projectObject) {
     this.project = projectObject;
    }

    public void setTracker(Tracker trackerObject) {
     this.tracker = trackerObject;
    }

    public void setStatus(Status statusObject) {
     this.status = statusObject;
    }

    public void setPriority(Priority priorityObject) {
     this.priority = priorityObject;
    }

    public void setAuthor(Author authorObject) {
     this.author = authorObject;
    }

    public void setAssigned_to(Assigned_to assigned_toObject) {
     this.assigned_to = assigned_toObject;
    }

    public void setParent(Parent parentObject) {
     this.parent = parentObject;
    }

    public void setSubject(String subject) {
     this.subject = subject;
    }

    public void setDescription(String description) {
     this.description = description;
    }

    public void setStart_date(String start_date) {
     this.start_date = start_date;
    }

    public void setDue_date(String due_date) {
     this.due_date = due_date;
    }

    public void setDone_ratio(float done_ratio) {
     this.done_ratio = done_ratio;
    }

    public void setIs_private(boolean is_private) {
     this.is_private = is_private;
    }

    public void setEstimated_hours(float estimated_hours) {
     this.estimated_hours = estimated_hours;
    }

    public void setCreated_on(String created_on) {
     this.created_on = created_on;
    }

    public void setUpdated_on(String updated_on) {
     this.updated_on = updated_on;
    }

    public void setClosed_on(String closed_on) {
     this.closed_on = closed_on;
    }
}
