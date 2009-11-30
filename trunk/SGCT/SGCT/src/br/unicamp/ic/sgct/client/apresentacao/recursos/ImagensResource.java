package br.unicamp.ic.sgct.client.apresentacao.recursos;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * An image bundle for this widgets images.
 */
public interface ImagensResource extends ImageBundle {
	@Resource("logo.jpg")
	public AbstractImagePrototype logo();

	public AbstractImagePrototype folder();

	public AbstractImagePrototype noimage();
	
	public AbstractImagePrototype mailgroup();
	
	public AbstractImagePrototype tasksgroup();
	
	public AbstractImagePrototype sent();
	
	public AbstractImagePrototype trash();
	
	@Resource("noimage.png")
	public AbstractImagePrototype treeLeaf();
	
	@Resource("default_photo.jpg")
	public AbstractImagePrototype defaultPhoto();

	public AbstractImagePrototype warningmsg();
	
	public AbstractImagePrototype successmsg();	
}