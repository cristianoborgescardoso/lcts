package sinalgo.models.EnergyModel.simple;

import java.util.Hashtable;
import sinalgo.models.EnergyModel.IEnergy;
import sinalgo.models.EnergyModel.EnergyMode;
import sinalgo.tools.Tools;

public class SimpleEnergy implements IEnergy
{

	private Float sleep;
	private Float transmission;
	private Float receive;
	private Float processing;
	private Float listen;
	private Float totalEnergy;

	private Hashtable<Double, Float> energyPerRound = new Hashtable<Double, Float>();

	public Hashtable<Double, Float> getEnergyPerRound()
	{
		return energyPerRound;
	}

	public SimpleEnergy()
	{
		this.sleep = Float.valueOf(0);
		this.transmission = Float.valueOf(0);
		this.receive = Float.valueOf(0);
		this.processing = Float.valueOf(0);
		this.listen = Float.valueOf(0);
		this.totalEnergy = 29160f;
	}

	public Float getTotalSpentEnergy()
	{
		return sleep + transmission + receive + processing + listen;
	}

	// private void calculateEnergyPerRound(Float value)
	// {
	// Double round = Tools.getGlobalTime();
	// if (energyPerRound.containsKey(round))
	// {
	// Float tmp = energyPerRound.get(round);
	// energyPerRound.put(round, tmp + value);
	// } else
	// {
	// energyPerRound.put(round, value);
	// }
	// }
	//
	private void calculateEnergyPerRound(Float value)
	{
		Double round = Tools.getGlobalTime();

		Float tmp = energyPerRound.get(round);
		if (tmp != null)
		{
			energyPerRound.put(round, tmp + value);
		} else
		{
			energyPerRound.put(round, value);
		}
	}

	public void spend(EnergyMode mode, float time)
	{
		switch (mode)
		{
			case LISTEN:
				listen += Config.ENERG_ESCUTA;
				calculateEnergyPerRound(Config.ENERG_ESCUTA);
				break;
			case RECEIVE:
				receive += Config.ENERG_RECEPCAO * time;
				calculateEnergyPerRound(Config.ENERG_RECEPCAO * time);
				break;
			case SEND:
				transmission += Config.ENERG_TRANSMISSAO * time;
				calculateEnergyPerRound(Config.ENERG_TRANSMISSAO * time);
				break;
			case SLEEP:
				sleep += Config.ENERG_SLEEP;
				calculateEnergyPerRound(Config.ENERG_SLEEP);
				break;
			case PROCESSING:
				processing += 0;
				calculateEnergyPerRound(0f);
				break;
			case MONITOR:
				break;
			default:
				break;
		}
	}

	public Float getEnergy()
	{
		return totalEnergy - getTotalSpentEnergy();
	}

	public Float getInitialEnergy()
	{
		return this.totalEnergy;
	}

}
