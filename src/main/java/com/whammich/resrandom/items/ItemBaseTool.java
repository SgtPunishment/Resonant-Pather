package com.whammich.resrandom.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;

import com.whammich.resrandom.items.types.ToolType;
import com.whammich.resrandom.utils.Reference;
import com.whammich.resrandom.utils.Register;
import com.whammich.resrandom.utils.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBaseTool extends Item implements IEnergyContainerItem {

	public static int send = 0;
	private IIcon[] icon = new IIcon[ToolType.values().length + 1];

	public ItemBaseTool() {
		super();
		setUnlocalizedName(Reference.MOD_ID + ".tool.");
		setCreativeTab(Register.CREATIVE_TAB);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

		if (!player.capabilities.isCreativeMode)
			extractEnergy(stack, send, false);

		return super.onItemUse(stack, player, world, x, y, z, hitSide, hitX, hitY, hitZ);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tabs, List list) {

		list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, ToolType.CREATIVE.ordinal()), ToolType.CREATIVE.capacity));

		for (int i = 1; i < ToolType.values().length; ++i) {
			list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, i), 0));
			list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, i), ToolType.values()[i].capacity));
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		if (stack.stackTagCompound == null)
			Utils.setDefaultEnergyTag(stack, 0);

		int currentEnergy = stack.stackTagCompound.getInteger("Energy");

		return 1.0 - ((double) currentEnergy / (double) ToolType.values()[stack.getItemDamage()].capacity);
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {

		if (Utils.displayShiftForDetail && !Utils.isShiftKeyDown())
			list.add(Utils.shiftForDetails());

		if (stack.stackTagCompound == null)
			Utils.setDefaultEnergyTag(stack, 0);

		if (Utils.isShiftKeyDown()) {
			list.add(Utils.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + ToolType.values()[stack.getItemDamage()].capacity + " RF");
			// list.add(Utils.ORANGE +
			// Utils.localizeFormatted("info.RArm.tooltip.peruse", "" + send) +
			// Utils.END);
		}
	}

	// Energy Stuff

	@Override
	public int receiveEnergy(ItemStack stack, int i, boolean simulate) {
		if (stack.stackTagCompound == null)
			Utils.setDefaultEnergyTag(stack, 0);

		int energy = stack.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(i, Math.min(ToolType.values()[stack.getItemDamage()].capacity - energy, ToolType.values()[stack.getItemDamage()].receive));

		if (!simulate) {
			energy += energyReceived;
			stack.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack stack, int extract, boolean simulate) {
		if (stack.stackTagCompound == null)
			Utils.setDefaultEnergyTag(stack, 0);

		int energy = stack.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(extract, Math.min(energy, send));

		if (!simulate) {
			energy -= energyExtracted;
			stack.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack stack) {
		if (stack.stackTagCompound == null)
			Utils.setDefaultEnergyTag(stack, 0);
		return stack.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack stack) {
		return ToolType.values()[stack.getItemDamage()].capacity;
	}

	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + ToolType.values()[stack.getItemDamage()].toString();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icon[0] = reg.registerIcon(Reference.MOD_ID + ":creativetool");
		icon[1] = reg.registerIcon(Reference.MOD_ID + ":leadstonetool");
		icon[2] = reg.registerIcon(Reference.MOD_ID + ":hardenedtool");
		icon[3] = reg.registerIcon(Reference.MOD_ID + ":redstonetool");
		icon[4] = reg.registerIcon(Reference.MOD_ID + ":resonanttool");
	}

}
