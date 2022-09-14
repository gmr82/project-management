package entities;

import java.util.Calendar;

public class Activity
{
  private int id;
  private String description;
  private Calendar initialDate;
  private Calendar finalDate;
  private User responsibleUser;
  // list private User collaborators;
  // list private String tasks; (tarefas a serem realizadas por cada profissional)
  
  public Activity (int id, String description, Calendar initialDate, Calendar finalDate, User responsibleUser)
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

  public Calendar getInitialDate ()
  {
    return initialDate;
  }

  public Calendar getFinalDate ()
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