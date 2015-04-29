package com.whammich.respather.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import baubles.api.BaubleType;
import baubles.api.IBauble;

import com.whammich.respather.Main;
import com.whammich.respather.utils.Reference;
import com.whammich.respather.utils.Register;

import cpw.mods.fml.common.Optional;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemBagBase extends Item implements IBauble {

	public ItemBagBase() {
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(Register.CREATIVE_TAB);
		this.setUnlocalizedName("respather.bag");
		this.setTextureName(Reference.MOD_ID + ":bag");
	}
	
	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return BaubleType.BELT;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(world.isRemote) {
			player.openGui(Main.modInstance, 0, world, player.chunkCoordX, player.chunkCoordY, player.chunkCoordZ);
		}
		return super.onItemRightClick(stack, world, player);
		
	}
	
}
