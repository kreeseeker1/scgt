package br.unicamp.ic.sgct.server.dominio.servicos;

public enum SecaoInfoGeral {
	SOBRE(1, "br/unicamp/ic/sgct/server/recursos/SobreAConferencia.html"),
	LOCAL_INSCRICAO(2, "br/unicamp/ic/sgct/server/recursos/SobreAConferencia.html"),
	DATAS_IMPORTANTES(3, "br/unicamp/ic/sgct/server/recursos/SobreAConferencia.html");

	private int secao;
	private String url;

	SecaoInfoGeral(int secao, String url) {
		this.secao = secao;
		this.url = url;
	}

	public int getSecao() {
		return secao;
	}

	public void setSecao(int secao) {
		this.secao = secao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String mapearUrlInfoGeral(int secao) {
		if ( SOBRE.getSecao() == secao ) {
			return SOBRE.getUrl();
		}

		if ( LOCAL_INSCRICAO.getSecao() == secao ) {
			return LOCAL_INSCRICAO.getUrl();
		}

		if ( DATAS_IMPORTANTES.getSecao() == secao ) {
			return DATAS_IMPORTANTES.getUrl();
		}

		return null;
	}
}