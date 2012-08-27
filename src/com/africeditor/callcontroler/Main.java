package com.africeditor.callcontroler;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Changeons le title de notre app pour plus de visibilité
	//	this.setTitle(getResources().getString(R.string.app_name));
	}

	/** Creation de menu. */
	@Override
	public boolean onCreateOptionsMenu(Menu mMenu) {

		//Création d'un sous-menu "mMenu"
		SubMenu lMenu = mMenu.addSubMenu(0, 0, 0, this.getResources().getString(R.string.lng));

		//Ajoutons 3 sous-menu avec des id différents
		lMenu.add(0, 1001, 0, "English").setIcon(android.R.drawable.ic_menu_preferences);
		lMenu.add(0, 1002, 0, "Francais");
		lMenu.add(0, 1003, 0, "Deutsch");
		//Retournons une valeur
		return true;

	}

	/** Gestion du menu */
	@Override
	public boolean onOptionsItemSelected(MenuItem mItem) {
		switch (mItem.getItemId()) {
		case 1001:setLocal(Locale.ENGLISH);
		break;

		case 1002:setLocal(Locale.FRENCH);
		break;

		case 1003:setLocal(Locale.GERMAN);
		break;

		}

		//Retournons une valeur
		return true;
	}

	/** Definition de la methode setLocal. */
	private void setLocal(Locale mLocale) {
		
		//Changeons l environnement 
		//Instancions les ressources de notre app
		Resources lRes = getResources();

		//Changeons les config:cad la langue de cette ressource
		Configuration lConfig = lRes.getConfiguration();

		//Donnons lui la valeur envoyée en parametre
		lConfig.locale = mLocale;

		//puis mettons le a jour
		lRes.updateConfiguration(lConfig, lRes.getDisplayMetrics());

		/** Mise a jour de cette config non supportée par ce framework so creons une copie de notre activity*/
		try {
			//Création d une copie de notre activity pour update la langue
			Context lContext = this.createPackageContext(this.getPackageName(), Context.CONTEXT_INCLUDE_CODE);

			//Lancons un nouvel intent
			Intent Lintent = new Intent(lContext, Main.class);

			//Puis démarrons notre activity
			startActivity(Lintent);

			//Terminons l'activity en cours car elle n'est plus utile puisqu elle est dans l ancienne langue
			finish();

		} catch (Exception mE) {

			//Ajout de try catch
			mE.printStackTrace();

		}


	}


}