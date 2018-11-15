package me.projeto.socket;

import me.projeto.erro.CustomErro;

/**
 * Classe de interface de Socket
 * 
 * @author UnixCF
 * @since 2018-11-14
 *
 */

public interface InterSocket {

	/**
	 * M�todo para preparar o sistemas Socket, aqui setamos a porta do servidor
	 * Socket. Observa��o: Este m�todo tamb�m � utilizado para conectar em um
	 * servidor socket.
	 * 
	 * @param endereco informar o endere�o IP do Socket.
	 * @param porta    informar a porta de conex�o ao Socket.
	 * @throws CustomErro informa os tipo de erro que est�o dando
	 */
	public abstract void PrepararSocket(String endereco, int porta) throws CustomErro;

	/**
	 * M�todo para cria��o do servidor/conex�o
	 */
	public abstract void CarregarSocket();

	/**
	 * M�todo para ouvir retorno da porta do socket
	 * 
	 * @throws CustomErro informa os tipo de erro que est�o dando
	 */
	public abstract void OuvirSocket() throws CustomErro;

}
