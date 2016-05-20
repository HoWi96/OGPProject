package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try {
			return new Unit(name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}
	
	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		return unit.getPosition();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return unit.getCubePosition().toArray();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try {
			unit.setName(newName);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		int weight = unit.getWeight();
		return weight;
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
			unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		int strength = unit.getStrength();
		return strength;
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		int agility = unit.getAgility();
		return agility;
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		int toughness = unit.getToughness();
		return toughness;
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		int maxHitPoints = unit.getMaxHitpoints();
		return maxHitPoints;
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		int hitPoints = unit.getHitpoints();
		return hitPoints;
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		int maxStamina = unit.getMaxStamina();
		return maxStamina;
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return (int) unit.getStamina();
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try {
			unit.advanceTime(dt);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try {
			unit.moveToAdjacent(dx, dy, dz);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		double speed = unit.getSpeed();
		return speed;
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		try {
			unit.startSprinting();
		} catch (IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		unit.stopSprinting();
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.isSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try {
			unit.moveTo(cube);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {
		try {
			unit.workAt(unit.getCubePosition().toArray());
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isWorking();
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		attacker.attack(defender);
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		try {
			unit.rest();
		} catch (IllegalStateException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		if (value) {
			unit.startDefaultBehavior();
		}
		else
			unit.stopDefaultBehaviour();
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.hasDefaultBehavior();
	}

}
