package entities;

public class Project
{
  private int id;
  private String description;
  private String initialDate;
  private String finalDate;
  private User coordinator;
  // list private User collaborators;
  // list private Activity activities;
  // ? ? values;
  private String validityPeriod;
  
  public Project (int id, String description, String initialDate, String finalDate, User coordinator,
  String validityPeriod)
  {
    this.id = id;
    this.description = description;
    this.initialDate = initialDate;
    this.finalDate = finalDate;
    this.coordinator = coordinator;
    this.validityPeriod = validityPeriod;
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

  public User getCoordinator ()
  {
    return coordinator;
  }

  public String getValidityPeriod ()
  {
    return validityPeriod;
  }

  @Override
  public String toString ()
  {
    return "Project [id=" + id + ", " +
           "coordinator=" + coordinator + ", " +
           "description=" + description + ", " +
           "initialDate=" + initialDate + ", " +
             "finalDate=" + finalDate + ", " +
        "validityPeriod=" + validityPeriod + "]";
  }

}