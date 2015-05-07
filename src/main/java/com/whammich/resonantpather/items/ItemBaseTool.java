package com.whammich.resonantpather.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;

import com.whammich.resonantpather.items.types.ToolType;
import com.whammich.resonantpather.utils.Reference;
import com.whammich.resonantpather.utils.Register;
import com.whammich.resonantpather.utils.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBaseTool extends Item implements IEnergyContainerItem {

	public static int send = 0;
	IIcon[] icon = new IIcon[ToolType.values().length];

	public ItemBaseTool() {
		super();

		setUnlocalizedName(Reference.MOD_ID + ".tool.");
        setCreativeTab(Register.CREATIVE_TAB);
        setMaxStackSize(1);
    }

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

		if (!player.capabilities.isCreativeMode && isValidTool(stack))
			extractEnergy(stack, send, false);

		return super.onItemUse(stack, player, world, x, y, z, hitSide, hitX, hitY, hitZ);
	}

//	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
//		InventoryBaubles BaubleBag = PlayerHandler.getPlayerBaubles(player);
//		if(!world.isRemote && BaubleBag.stackList[3] != null && player.isSneaking()){
//			player.openGui(resonantpather.modInstance, BaubleBag.stackList[3].getItemDamage(), world, (int) player.posX, (int) player.posY, (int) player.posZ);
//		}
//		return stack;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item item, CreativeTabs tabs, List list) {

		list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, ToolType.CREATIVE.ordinal()), ToolType.CREATIVE.capacity));

		for (int i = 1; i < ToolType.values().length; ++i) {
			list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, i), 0));
			list.add(Utils.setDefaultEnergyTag(new ItemStack(item, 1, i), getMaxEnergyStored(new ItemStack(item, 1, i))));
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return stack.getItemDamage() != ToolType.CREATIVE.ordinal();
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0 - ((double) getEnergyStored(stack) / (double) getMaxEnergyStored(stack));
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {

		if (Utils.displayShiftForDetail && !Utils.isShiftKeyDown())
			list.add(Utils.shiftForDetails());

		if (Utils.isShiftKeyDown()) {
            if (isValidTool(stack))
                list.add(Utils.localize("info.resonantpather.tooltip.tool.charge") + ": " + getEnergyStored(stack) + " / " + getMaxEnergyStored(stack) + " RF");
            else
                list.add(Utils.localize("info.resonantpather.tooltip.null"));
        }
	}

    public String getUnlocalizedName(ItemStack stack) {
        if (isValidTool(stack))
            return super.getUnlocalizedName(stack) + ToolType.values()[stack.getItemDamage()].toString();
        else
            return super.getUnlocalizedName(stack) + "fallback";
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < ToolType.values().length; i++)
            icon[i] = reg.registerIcon(Reference.MOD_ID + ":pathertool-" + ToolType.values()[i].toString());
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (isValidTool(new ItemStack(this, 1, meta)))
            return icon[meta];
        else
            return itemIcon;
    }

    public EnumRarity getRarity(ItemStack stack) {
        if (isValidTool(stack))
            return ToolType.values()[stack.getItemDamage()].rarity;

        return EnumRarity.common;
    }

    private boolean isValidTool(ItemStack stack) {
        return stack.getItemDamage() < ToolType.values().length;
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
}
