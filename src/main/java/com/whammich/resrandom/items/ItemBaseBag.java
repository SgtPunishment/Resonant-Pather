package com.whammich.resrandom.items;

import net.minecraft.client.renderer.texture.IIconRegister;
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
	}

	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.BELT;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
	}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
	}

	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if (world.isRemote) {
			player.openGui(ResRandom.modInstance, 0, world, player.chunkCoordX, player.chunkCoordY, player.chunkCoordZ);
		}
		return super.onItemRightClick(stack, world, player);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icon[0] = reg.registerIcon(Reference.MOD_ID + ":basicbag");
		icon[1] = reg.registerIcon(Reference.MOD_ID + ":improvedbag");
		icon[2] = reg.registerIcon(Reference.MOD_ID + ":advancedbag");

	}
	
	public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + BagType.values()[stack.getItemDamage()].toString();
    }
}
