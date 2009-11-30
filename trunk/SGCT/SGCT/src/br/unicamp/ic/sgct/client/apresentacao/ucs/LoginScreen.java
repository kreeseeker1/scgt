/*
 * Copyright 2007 Aditya Kapur <addy AT gwtiger.org>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.unicamp.ic.sgct.client.apresentacao.ucs;

import org.gwtiger.client.screen.BaseScreen;
import org.gwtiger.client.widget.field.PasswordFieldWidget;
import org.gwtiger.client.widget.field.TextFieldWidget;
import org.gwtiger.client.widget.panel.ButtonPanel;
import org.gwtiger.client.widget.panel.ValidatePanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;

/**
 * @author Addy
 * 
 */
public class LoginScreen extends BaseScreen {
	private static final String USERID_COOKIE = "SGCTUserid";

	private ValidatePanel panelValidate = new ValidatePanel();

	private TextFieldWidget txtLogin = new TextFieldWidget("Username");

	private PasswordFieldWidget txtPassword = new PasswordFieldWidget("Senha");

	private ButtonPanel btnPanel;

	public LoginScreen() {
		txtLogin.setRequired(true);
		txtPassword.setRequired(true);

		panelValidate.setWidth("100%");
		panelValidate.setSpacing(10);

		panelValidate.add(txtLogin);
		panelValidate.add(txtPassword);

		btnPanel = new ButtonPanel();
		btnPanel.addStyleDependentName("");

		btnPanel.addSaveButton("Login");
		btnPanel.addClearButton();
		panelValidate.add(btnPanel);

		dockPanel.add(panelValidate, DockPanel.NORTH);

		initWidget(dockPanel);
	}

	public boolean validate() {
		return panelValidate.validate();
	}

	public void clear() {
		setErrorText("");
		panelValidate.clear();
	}

	public void checkIfLogedIn() {

		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
				showLoading(false);
/*				User user = (User) result;
				// clear the login screen and show the main screen
				if (user != null) {
					setUser(user);
					GWTigerDemo.get().setMainScreen(user);
				}*/
			}

			public void onFailure(Throwable ex) {
				showLoading(false);
/*				setErrorText(getMessages().errorLogin(ex.getMessage()));*/

			}
		};
		showLoading(true);
/*		getService().isSessionAlive(callback);*/
		txtLogin.setText(getCookie(USERID_COOKIE));
	}

/*	public abstract void save();*/
	
	protected void checkLogin() {

		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
				showLoading(false);
				// clear the loginscreen

/*				User user = (User) result;
				if (user != null) {
					clear();
					if (rememberMe.isChecked())
						setCookie(USERID_COOKIE, user.getUserName());
					else
						setCookie(USERID_COOKIE, "");
					GWTigerDemo.get().setMainScreen(user);
				} else {
					setErrorText(getMessages().errorLoginInvalid(
							getMessages().lblUserName(),
							getMessages().lblPassword()));
				}*/
			}

			public void onFailure(Throwable ex) {
				showLoading(false);
/*				setErrorText(getMessages().errorLogin(ex.getMessage()));*/
			}
		};
		showLoading(true);
/*		getService().checkLogin(txtLogin.getText(), txtPassword.getText(),
				callback);*/
	}

	public ButtonPanel getBtnPanel() {
		return btnPanel;
	}

	public void setBtnPanel(ButtonPanel btnPanel) {
		this.btnPanel = btnPanel;
	}
}