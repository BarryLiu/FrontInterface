package com.ios.sinian.AddressBook;

import android.view.View;

public class ViewOperation {
	private View view;
	private boolean remove;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public ViewOperation(View view, boolean remove) {
		this.view = view;
		this.remove = remove;
	}

}
