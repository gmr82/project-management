package entities;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Iterator;

public class Project
{

  private static int nextId;
  
  public enum ProjectType
	{
		EXT("Extensão"),
		PES("Pesquisa"),
		INO("Inovação"),
		TEC("Tecnologia");

		private final String description;

		ProjectType (String description)
		{
      this.description = description;
    }
	}
	private ProjectType projectType;
  public enum ProjectStatus
	{
		EPC("Em processo de criação"),
		INI("Iniciado"),
		EAN("Em andamento"),
		CON("Concluído");

		private final String description;

		ProjectStatus (String description)
		{
      this.description = description;
    }
	}
	private ProjectStatus projectStatus;
  
  private final int id;
  private String description;
  
  private Calendar initialDate;
  private Calendar finalDate;
  private User coordinator;
  // list private User collaborators;
  // list private Activity activities;
  // ? ? values;
  private Calendar validityPeriod;
  
  Project (ProjectType projectType, int id, String description, User coordinator, ProjectStatus projectStatus,Calendar initialDate, Calendar finalDate, Calendar validityPeriod)
  {
    this.projectType = projectType;
    this.id = id;
    this.description = description;
    this.coordinator = coordinator;
    this.projectStatus = projectStatus;
    this.initialDate = initialDate;
    this.finalDate = finalDate;
    this.validityPeriod = validityPeriod;
  }

  @Override
  public String toString ()
  {
    return "Project [id = " + id + ", " +
           "projectType = " + projectType.description + ", " +
         "projectStatus = " + projectStatus.description + ", " +
           "description = " + description + ", " +
           "coordinator = " + coordinator.getEmail() + ", " +
           "initialDate = " + initialDate + ", " +
             "finalDate = " + finalDate + ", " +
        "validityPeriod = " + validityPeriod + "]";
  }

  private static int getNextId ()
  {
    return nextId++;
  }


  private static Project searchProject (int id, ArrayList<Project> projects)
  {
    Iterator <Project> iterator = projects.iterator();
      Project project;
      
      while (iterator.hasNext())
      {
        project = iterator.next();
        if (id == project.id) return project;
      }
    return null;
  }
  
  private static void searchProject (ArrayList<Project> projects)
  {
    System.out.print("# Pesquisar projeto #");

    boolean bool = true;
    int id = -1;
    while (bool)
		{
			System.out.print("\n  id: ");
			try 
			{
				id = Integer.parseInt(Main.read.nextLine());
				bool = false;
			}
			catch (Exception exception)
			{
				System.out.print("  Valor inválido!");
			}
		}

		Project project = Project.searchProject(id, projects);
		if (project == null)
		{
			System.out.println("  id não cadastrado!");
			return;
		}
    project.show();
	}
  
  static void showProjectsIn (ArrayList<Project> projects)
  {
    projects.forEach(project -> System.out.println(project.toString()));
  }

  static void createProject (User coordinator, ArrayList<Project> projects)
  {
    System.out.println("# Criar projeto #");

    if (!coordinator.checkPermission())
    {
      System.out.println("  Tipo de usuário inválido!");
      return;
    }

    System.out.print("  [");
		for (ProjectType projectType: ProjectType.values())
		{
			System.out.print(projectType.ordinal() + "=" + projectType.description + " ");
		}
		System.out.print("\b]");

		ProjectType projectType = null;

		boolean bool = true;
		while (bool)
    {
			System.out.print("\n  Tipo de projeto: ");
			try 
			{
				projectType = ProjectType.values()[Integer.parseInt(Main.read.nextLine())];
				bool = false;
			}
			catch (Exception exception)
			{
				System.out.print("  Valor inválido!");
				
			}
		}

    System.out.print("  Descrição: ");
		String description = Main.read.nextLine();

    Project project = new Project(projectType, getNextId(), description, coordinator, Project.ProjectStatus.EPC, null, null, null);

		projects.add(project);
		Main.projects.add(project);

		System.out.println("  Projeto criado!");
		
		while (project.access(projects));
  }

  static void editProject (User coordinator, ArrayList<Project> projects)
  {
    System.out.println("# Criar projeto #");

    if (!coordinator.checkPermission())
    {
      System.out.println("  Tipo de usuário inválido!");
      return;
    }

    System.out.print("  [");
		for (ProjectType projectType: ProjectType.values())
		{
			System.out.print(projectType.ordinal() + "=" + projectType.description + " ");
		}
		System.out.print("\b]");

		ProjectType projectType = null;

		boolean bool = true;
		while (bool)
    {
			System.out.print("\n  Tipo de projeto: ");
			try 
			{
				projectType = ProjectType.values()[Integer.parseInt(Main.read.nextLine())];
				bool = false;
			}
			catch (Exception exception)
			{
				System.out.print("  Valor inválido!");
				
			}
		}

    System.out.print("  Descrição: ");
		String description = Main.read.nextLine();

    Project project = new Project(projectType, getNextId(), description, coordinator, Project.ProjectStatus.EPC, null, null, null);

		projects.add(project);
		Main.projects.add(project);

		System.out.println("  Projeto criado!");
		
		while (project.access(projects));
  }


  private boolean access (ArrayList<Project> projects)
	{
    System.out.println("  [Projeto: " + this.id + "]");
		System.out.print("  Selecione:" +
                   "\n    1) resumo;" +
                   "\n    2) editar atividades*" + //(atv.size() > 0 ? " (" + atv.size() + ")": "") + ";" +
                   "\n    3) " + (coordinator == null ? "atribuir": "alterar") + " coordenador;" +
                   "\n    4) remover*;" +
                   "\n    0) Sair." +
                   "\n    >> ");
    
		try 
		{
      switch(Integer.parseInt(Main.read.nextLine()))
			{
        case 0: return false;
				case 1: show(); return true;
				case 2: /*activities()*/; return true;
				case 3: changeCoordinator(); return false;	
				case 4: remove(projects); return false;
				default: System.out.println("    Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
				System.out.println("    Valor inválido!");
		}
		return true;
	}


  static boolean menu ()
  {
    System.out.println("  [menu_projetos]");
    System.out.print("  Selecione:" +
                   "\n    1) listar projetos;" +
                   "\n    2) pesquisar projeto;" +
                   "\n    0) Voltar." +
                   "\n    >> ");
    
    try 
    {
      switch(Integer.parseInt(Main.read.nextLine()))
      {
        case 0: System.out.println("  Voltando...");; return false;
        case 1: showProjectsIn(Main.projects); return true;
        case 2: searchProject(Main.projects); return true;
        default: System.out.println("  Opção inexistente!"); return true;
      }
    }
    catch (Exception exception)
    {
      System.out.println("  Valor inválido!");
      return true;
    }
  }


  public void show ()
	{
		System.out.println(this.toString());
	}


  private void changeCoordinator ()
	{
    System.out.print("# " + (coordinator == null ? "Atribuir": "Alterar") + " coordenador do projeto #" +
    "\n  Email: ");
    User user = User.searchUser(Main.read.nextLine(), Main.users);
    if (user == null)
    {
      System.out.println("  Email não cadastrado!");
      return;
    }
    
    if (user.checkPermission())
    {
      System.out.println("  Tipo de usuário inválido!");
      return;
    }
    
		this.coordinator = user;
		System.out.println("  Novo coordenador atribuído!");
	}
  
	private void remove (ArrayList<Project> projects)
  {}

}