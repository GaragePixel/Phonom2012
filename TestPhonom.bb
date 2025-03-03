Graphics3D 1024,768,24,7
ClsColor 10,10,20

Const TwoPi# = 6.28318530
Const key=30
Dim x(key)
Global vVGraphicsHeight=GraphicsHeight()/2


Global Sound0 = LoadSound("son01.mp3")
;LoopSound sound0
Global sound




For i% = 1 To key
	x(i%) = i%+Rand(-100,100)
Next

; =================
; Class ONDE
; =================
; >>> Globals
Global Amplitude#=100
Global periodTime#=1
Global periodSpace#=1
Global OriginPhase#=0
; >>> Method
Function DrawWave(i%, time#) ; // OnDraw
	Local wave = CalcWave#(i%, time#)
	Color 255,255,100:Plot tempX#,vVGraphicsHeight+(wave/10) ; // Trace le pointer de l'onde depuis la fonction d'onde
Sound = sound0
LoopSound sound0
StopChannel soundchannel
soundchannel = PlaySound(Sound0)

ChannelPitch soundchannel , (010000-(wave))*20+Rnd(0,1000)
	If tempX#>GraphicsWidth():tempX#=0:Flip:Cls:EndIf ; // Retour ? la ligne si le pointer sort ? droite, puis affiche la courbe
End Function
Function CalcWave#(i%, time#) ; // OnUpdate
	Return Amplitude#*Sin((TwoPi#*(time#/periodTime#))-(TwoPi#*(x(i%)/periodSpace#))+OriginPhase#) ; // Fonction scalaire d'onde unidimentionnelle
End Function
; >>> Temporaire
Global tempX# ; // Sert pour le retour ? la ligne du trac? de la courbe


; ///////////////////////////
; /// Pipeline App
; ///////////////////////////
Mainloop()
End()

; ///////////////////////////
; /// Mainloop
; ///////////////////////////
Function Mainloop()
	Local i%,time#
	Repeat
		If KeyDown(16) Then Amplitude# = Amplitude# + .1
		If KeyDown(30) Then Amplitude# = Amplitude# - .1
		If KeyDown(17) Then periodTime# = periodTime# + .0001
		If KeyDown(31) Then periodTime# = periodTime# - .0001
		If KeyDown(18) Then OriginPhase# = OriginPhase# + 1
		If KeyDown(32) Then OriginPhase# = OriginPhase# - 1
		time# = time# + .1:tempX# = tempX# + .1 ; // On avance dans le temps...
		i% = i% + 1
		If i% > key Then i% = 0
		DrawWave(i%, time#) ; // Calcule et trace l'onde
	Until KeyHit(1)
End Function

