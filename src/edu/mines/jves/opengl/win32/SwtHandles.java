package edu.mines.jves.opengl;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.OS;

/**
 * SWT handles required to create, access, and destroy OpenGL contexts.
 * For internal use only. This class is platform-dependent.
 * @author Dave Hale, Colorado School of Mines
 * @version 2004.11.30
 */
class SwtHandles {

  // Generic SWT.
  Drawable drawable;
  GCData data;
  long handle;

  // For X Windows.
  long xdisplay;
  long xdrawable;
  long xgc;

  // For Microsoft Windows.
  long hwnd;
  long hdc;

  SwtHandles(Canvas canvas) {
    drawable = canvas;
    data = new GCData();
    handle = canvas.handle;
    hwnd = canvas.handle;
    hdc = drawable.internal_new_GC(data);
    if (hdc==0)
      throw new RuntimeException("cannot new hdc");
  }
  void dispose() {
    try {
      drawable.internal_dispose_GC((int)hdc,data); // not 32-bit clean!
    } catch (SWTException swte) {
      throw new RuntimeException("cannot dispose hdc");
    }
  }
}
