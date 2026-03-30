package metier;

public class TesteSerialisation 
{
	public static void main(String[] args) 
	{
		GlobalCLass global = new GlobalCLass();

		Sushi s = new Sushi();
		s.setId(1);
		s.setNom("Saumon");
		s.setPrix(5.5);

		Boisson b = new Boisson();
		b.setId(2);
		b.setNom("Coca");
		b.setPrix(2.5);

		global.getCatalogue().put(s.getId(), s);
		global.getCatalogue().put(b.getId(), b);

		XMLStorage.encoder(global, "../bdd.xml");

		GlobalCLass loaded = XMLStorage.decoder("../bdd.xml");

		System.out.println("Nb produits chargés : " + loaded.getCatalogue().size());
		
		//AppConfig first = new AppConfig();
		//first.setXmlPath("C:\\Users\\timeo\\Desktop\\Nouveau dossier\\AdminPage\\src\\main\\webapp\\WEB-INF\\appConfig.xml");
		//XMLStorage.encoderConfigFile(first, "src/main/webapp/WEB-INF/appConfig.xml");
		
		
	}
}
