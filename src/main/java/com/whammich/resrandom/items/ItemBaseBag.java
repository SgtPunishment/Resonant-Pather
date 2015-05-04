package com.whammich.resrandom.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import baubles.api.BaubleType;
import baubles.api.IBauble;

import com.whammich.resrandom.ResRandom;
import com.whammich.resrandom.items.types.BagType;
import com.whammich.resrandom.utils.Reference;
import com.whammich.resrandom.utils.Register;
import com.whammich.resrandom.utils.Utils;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemBaseBag extends Item implements IBauble {

	private IIcon[] icon = new IIcon[BagType.values().length + 1];

	public ItemBaseBag() {
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(Register.CREATIVE_TAB);
		setUnlocalizedName(Reference.MOD_ID + ".bag.");
	}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase entity) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase entity) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.BELT;
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase entity) {
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase entity) {
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase entity) {
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.openGui(ResRandom.modInstance, stack.getItemDamage(), world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
    	for (int i = 0; i < BagType.values().length; ++i) {
    		icon[i] = reg.registerIcon(Reference.MOD_ID + ":" + BagType.values()[i].toString() + "bag");
        }
	}
	
	public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + BagType.values()[stack.getItemDamage()].toString();
    }
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
    	for (int i = 0; i < BagType.values().length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
    
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {

		if (Utils.displayShiftForDetail && !Utils.isShiftKeyDown())
			list.add(Utils.shiftForDetails());

		if (Utils.isShiftKeyDown()) {
			list.add(Utils.localize("info.tooltip.bag.slots") + ": " + BagType.values()[stack.getItemDamage()].slots);
		}
	}
}
