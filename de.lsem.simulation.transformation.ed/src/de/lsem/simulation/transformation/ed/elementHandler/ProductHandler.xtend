package de.lsem.simulation.transformation.ed.elementHandler

import com.google.inject.Inject
import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper

class ProductHandler {

	@Inject
	extension ElementHelper
	@Inject
	extension ConnectionHandler

	private def productName(ISource it)'''
		«processedObject.name»
	'''

	def writeProduct(ISource it, int x, int y) '''
		{Atom: «productName»}	
		sets;
		BaseClass;
		CreateAtom(a, Up(s), [], 1, false);
		int023([«productName»], 12615680, 8273);
		Set(Icon(a), 
			RegisterIcon(IconsDir([bmp\atoms\product.bmp]), [product]));
		AddModel3D(
			RegisterModel3D(Model3DDir([\box-closed.wrl]), [box-closed.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\box-opened.wrl]), [box-opened.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\pallet-wood.wrl]), [pallet-wood.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\pallet-plastic.wrl]), [pallet-plastic.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\oildrum.wrl]), [oildrum.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\crate.wrl]), [crate.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		SetMaterial(
			RegisterMaterial([Default], 8421504, 8421504, 3289650, 0, 0.100000001490116, 0, false, false, 1, 0), 1, a);
		Set(Version(a), 0);
		SetTreeIcon(pDir([Media\Icons\Product.ico]));
		Set(Info, [A general product.
		
		General
		-----------
		Put a product in your model and connect it's central channel 
		with the input channel of a source to generate multiple instances 
		of the product during running.
		
		Notes:
		---------
		For cylinder shaped, horizontally oriented 3D icons set ySize 
		equal to zSize.
		For cylinder shaped, vertically oriented 3D icons set xSize 
		equal to ySize.
		For sphere set xSize equal to ySize and zSize.
		
		Last revision:
		------------------
		September 27, 2003
		]);
		Set(DdbRec, [>t-nodestroy:1.]);
		«writeInt001»		
		«createConnection(getId(name), getId)»
		SetLoc(«x-2»,«y»,0);
		SetSize(1, 1, 1);
		LockPosition(false);
		LockSize(false);
		DisableIconRotation(false);
		SetProductCode([]);
		CreateAttributes(2);
		SetAttributeName(r(1), [Icon3D]);
		SetAttributeName(r(2), [Hide2DIcon]);
		SetAtt(r(1), 11);
		SetAtt(r(2), 1);
		int024;
		Set(OnCreation, [{ In the OnInit event handler some edit fields are registered. }
		{ This is ok for the product in the library tree, but not for the  }
		{ products in the model, so erase the OnInit expression.       }
		Set(OnInit, [])
		]);
		Set(OnReset, [If(
		Label( [t-nodestroy] , c) = 0,
		DestroyAtom
		)
		]);
		Set(OnUser, [Do(
		{ Delete any current instances of this form.} 
		GuiDestroy([Product]),
			
		{ Register the GUI-form for this atom.}
		GuiRegister(PDir([\Atoms\Product.gui]), 1),
			
		{ Show the GUI-form.}
		GuiCreate([Product], [Product], 0, c, 0, 1)
		)
		]);
		Set(On2DDraw, [if(
		c.DrawFlag,
			 
		{--External atom(s) control product rotationas--}
		do(
		{--Variables for rotationas in order to compensate change of transportation direction--}
		RotateCoords(c.DrawRotationas, 0, 0, 1),
			  
		{--Variables for translation in order to compensate change of transportation direction--}
		TranslateCoords(c.DrawXtranslate, c.DrawYtranslate, 0),
			  
		{--Rotated shape--}
		dishape(0, 0 ,c.DrawXsize, c.DrawYsize, 0.01, ColorBlack, color(c))
		),
			 
			 {--Standard--}
			 do(
			  c.DrawXsize := xSize(c),
			  c.DrawYsize := ySize(c),
			  diBmp(0, 0, Hide2DIcon * Icon(c), 1, 5)
			 )
			 
			)
			]);
			Set(On3DDraw, [do(
			
		 if(
		 {--Product rotationas has been changed by external atom (e.g. Corner Transfer Unit)--}
		 c.DrawFlag,
		 do(
		 {--Variables for rotationas in order to compensate change of transportation direction--}
		 RotateCoords(c.DrawRotationas,0,0,1),
		 
		 {--Variables for translation in order to compensate change of transportation direction--}
		 TranslateCoords(c.DrawXtranslate, c.DrawYtranslate,0)
		 ),
		 {--Standard--}
		 do(
		 c.DrawXsize := xSize(c),
		 c.DrawYsize := ySize(c)
		 )
		 ),
			
		 Case(
		 Icon3D,
		 {** Cube  **}
		 di3DBox(0, 0, 0, c.DrawXsize, c.DrawYsize, zSize, Color),
		 {**  Picture Cube **}
		 di3DBox(0, 0, 0, c.DrawXsize, c.DrawYsize, zSize, Color, 0, 0, Icon, 0, 100, 100),
		 {** Cylinder Horizontal **}
		 Do(
		 c.CenterPoint := Max(c.DrawYsize / 2, zSize / 2),
		 Cylinder(0, c.CenterPoint, c.CenterPoint, c.DrawXsize, 360, c.CenterPoint, 0, 0, 0, Color)
		 ),
		 {** Cylinder Vertical **}
		 Do(
		 c.CenterPoint := Max(c.DrawXsize / 2, c.DrawYsize / 2),
		 Cylinder(c.CenterPoint, c.CenterPoint, 0, zSize, 360, c.CenterPoint, 0, 90, 0, Color)
		 ),
		 {** Ball **}
		 Do(
		 c.CenterPoint := Max(c.DrawXsize / 2, Max(c.DrawYsize / 2, zSize / 2)),
		 Ball( c.CenterPoint, c.CenterPoint, c.CenterPoint, c.CenterPoint, 180, 360, 0, 0, 0, Color)
		 ),
		 {** Cone **}
		 Do(
		 c.CenterPoint := Max(c.DrawXsize, c.DrawYsize),
		 Cone(c.CenterPoint / 2, c.CenterPoint / 2, 0, zSize, c.CenterPoint / 2, 0, 0, 90, 0, Color)
		 ),
		 {** Barrel Horizontal **}
		 Do(
		 c.CenterPoint := Max(c.DrawYsize / 2, zSize / 2),
		 Cylinder(0, c.CenterPoint, c.CenterPoint, c.DrawXsize, 360, c.CenterPoint, 0, 0, 0, Color),
		 Cone(0, c.CenterPoint, c.CenterPoint, 0, c.CenterPoint, 0, 0, 0, 0, Color),
		 Cone(c.DrawXsize, c.CenterPoint, c.CenterPoint, 0, c.CenterPoint, 0, 0, 0, 0, Color)
		 ),
		 {** Barrel Vertical **}
		 Do(
		 c.CenterPoint := Max(c.DrawXsize / 2, c.DrawYsize / 2),
		 Cylinder(c.CenterPoint, c.CenterPoint, 0, zSize, 360, c.CenterPoint, 0, 90, 0, Color),
		 Cone(c.CenterPoint, c.CenterPoint, zSize, 0, c.CenterPoint, 0, 0, 90, 0, Color),
		 Cone(c.CenterPoint, c.CenterPoint, 0, 0, c.CenterPoint, 0, 0, 90, 0, Color)
		 ),
		 {** Pallet **}
		 Do(
		 di3DBox(0, 0, 0, c.DrawXsize, 0.1, zSize - 0.05, Color),
		 di3DBox(0, /( c.DrawYsize, 2) - 0.05, 0, c.DrawXsize, 0.1, zSize - 0.05, Color),
		 di3DBox(0, c.DrawYsize - 0.1, 0, c.DrawXsize, 0.1, zSize - 0.05, Color),
		 c.CenterPoint := c.DrawXsize / 5,
		 Repeat(
		  5,
		  di3DBox(Count * c.CenterPoint, 0, zSize - 0.05, -0.1, c.DrawYsize, 0.05, Color)
		 )
		 ),
		 {** Simple Person **}
		 Do(
		 c.CenterPoint := c.DrawYsize / 2,
		 {head} Ball( 0, c.CenterPoint, 1.8, 0.1, 180, 360, 0, -90 ,0, 7846395),
		 {neck} Cylinder(0, c.CenterPoint, 1.65, 0.1, 360, 0.05, 0, 90, 0, 7846395),
		 {shoulders}EllipseCone(0, c.CenterPoint, 1.52, 0.15, 360, 0.08, 0.05, 0.12, 0.2, 0, 90, 0, Color),
		 {arm 1}  Cone(0, 0.15 + c.CenterPoint, 1.5, 0.5, 0.05, 0.02, 20, -80, 0, Color),
		 {arm 2}  Cone(0, -0.15 + c.CenterPoint, 1.5, 0.5, 0.05, 0.02, 20, -80, 0, Color),
		 {hand 1} Cone(0.08, -0.12 + c.CenterPoint, 1.02, 0.1, 0.02, 0.02, 20, -80, 0, 7846395),
		 {hand 2}  Cone(0.08, 0.18 + c.CenterPoint, 1.02, 0.1, 0.02, 0.02, 20, -80, 0, 7846395),
		 {body}  EllipseCone(0,c.CenterPoint, 1.07, 0.45, 360, 0.12, 0.2, 0.08, 0.1, 0, 90, 0, Color),
		 {waist}  EllipseCone(0,c.CenterPoint, 0.92, 0.15, 360, 0.08, 0.1, 0.11, 0.15, 0, 90, 0, ColorGray),
		 {leg 1}  Cone(0, 0.07 + c.CenterPoint, 0, 0.92, 0.03, 0.08, 0, 90, 0, ColorGray),
		 {leg 2}  Cone(0, -0.07 + c.CenterPoint, 0, 0.92, 0.03, 0.08, 0, 90, 0, ColorGray),
		 {foot 1}  EllipseCone(0, 0.07 + c.CenterPoint, 0,0.2, 180, 0.01, 0.03, 0.04, 0.05, 0, 0, 0, ColorBlack),
		 {foot 2}  EllipseCone(0, -0.07 + c.CenterPoint, 0, 0.2, 180, 0.01, 0.03, 0.04, 0.05, 0, 0, 0, ColorBlack)
		 ),
		 {** Closed Carton Box **}
		 DrawModel3D(Model3D(1, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize),
		 {** Open Carton Box **}
		 DrawModel3D(Model3D(2, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize),
		 {** Pallet - Wood **}
		 DrawModel3D(Model3D(3, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize),
		 {** Pallet - Plastic **}
		 DrawModel3D(Model3D(4, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize),
		 {** Oildrum **}
		 DrawModel3D(Model3D(5, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize),
		 {** Crate **}
		 DrawModel3D(Model3D(6, c), 0, 0, 0, c.DrawXsize, c.DrawYsize, zSize)
		 )
			
			)
		]);
		SetStatus(0);
		int018;
	'''

	private def writeInt001(ISource it) '''
		int001(«getId(name)»);		
	'''
}
