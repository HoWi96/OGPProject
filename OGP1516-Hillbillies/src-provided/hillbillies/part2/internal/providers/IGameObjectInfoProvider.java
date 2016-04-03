package hillbillies.part2.internal.providers;

import hillbillies.common.internal.providers.UnitInfoProvider;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.items.rawmaterials.Boulder;
import hillbillies.model.items.rawmaterials.Log;

public interface IGameObjectInfoProvider extends UnitInfoProvider {

	public boolean isCarryingLog(Unit unit);

	public boolean isCarryingBoulder(Unit unit);
	
	public Faction getFaction(Unit unit);
	
	public int getFactionIndex(Faction faction);
	
	public int getExperiencePoints(Unit unit);
	
	public String getFactionName(Faction faction);

	public double[] getPosition(Boulder object);
	public double[] getPosition(Log object);
}
