package org.projeto.importante.util;

import java.io.DataOutputStream;
import java.net.Socket;

public abstract class ComunicarUtil {
	
	
	public abstract void Preparando(String ip,int porta);
	public abstract void Iniciar();
	
}
