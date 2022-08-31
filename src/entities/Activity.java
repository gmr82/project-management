package entities;

public class Activity
{
  private int id;
  private String description;
  private String initialDate;
  private String finalDate;
  private User responsibleUser;
  // list private User collaborators;
  // list private String tasks; (tarefas a serem realizadas por cada profissional)
  
  public Activity (int id, String description, String initialDate, String finalDate, User responsibleUser)
  {
    this.id = id;
    this.description = description;
    this.initialDate = initialDate;
    this.finalDate = finalDate;
    this.responsibleUser = responsibleUser;
  }

  public int getId ()
  {
    return id;
  }

  public String getDescription ()
  {
    return description;
  }

  public String getInitialDate ()
  {
    return initialDate;
  }

  public String getFinalDate ()
  {
    return finalDate;
  }

  public User getResponsibleUser ()
  {
    return responsibleUser;
  }

  @Override
  public String toString ()
  {
    return "Activity [id=" + id + ", " +
            "description=" + description + ", " +
            "initialDate=" + initialDate + ", " +
              "finalDate=" + finalDate + ", " +
        "responsibleUser=" + responsibleUser + "]";
  }

}