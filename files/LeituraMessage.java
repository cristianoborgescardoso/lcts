package projects.localizationVANT.nodes.messages;

//import projects.PIF.nodes.messages.INFMessage;
import sinalgo.nodes.messages.Message;

public class LeituraMessage extends Message {

	public int kwAtual;
	public int id;
	
    public LeituraMessage(int id, int kwAtual) {
        this.kwAtual = kwAtual;
        this.id = id;
    }

	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return new LeituraMessage(this.id, this.kwAtual);
	}

	public int getKwAtual() {
		return kwAtual;
	}

	public void setKwAtual(int kwAtual) {
		this.kwAtual = kwAtual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
