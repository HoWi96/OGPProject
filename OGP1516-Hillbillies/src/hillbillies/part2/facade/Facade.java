package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade extends hillbillies.part1.facade.Facade implements IFacade {

	@Override
	@Deprecated
	public void advanceTime(Unit unit, double dt) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");	
	}

	@Override
	@Deprecated
	public void work(Unit unit) throws ModelException {
		throw new NoSuchMethodError("This method no longer needs to be supported");	
	}

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try {
			World world = new World(terrainTypes,modelListener);
			return world;
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getNbCubesX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getNbCubesY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getNbCubesZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws IllegalArgumentException, IllegalStateException {
		//try {
			world.advanceTime(dt);
		//} catch (Exception e) {
		//	throw new ModelException();
		//}
		
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		try {
			int[] position = {x,y,z};
			return world.getCubeType(position);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		try {
			int[] position = {x,y,z};
			world.setcubeType(value, position);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
		
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.getConnectedToBorder().isSolidConnectedToBorder(x, y, z);
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		Unit unit = world.createRandomUnit(enableDefaultBehavior);
		world.addUnit(unit);
		System.out.println("Spawn unit "+unit.getName());
		return unit;
		
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		try {
			System.out.println("add unit "+unit.getName());
			world.addUnit(unit);
		} catch (IllegalArgumentException e) {
			throw new ModelException();
		}
		
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getAllUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return unit.isAlive();
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getXP();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		try {
			int[] position = new int[]{x, y, z};
			unit.workAt(position);
		} catch (Exception e) {
			throw new ModelException();
		}
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.getAllUnits();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getAllFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		return boulder.getPosition();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.getAllBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		return log.getPosition();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.getAllLogs();
	}

}
