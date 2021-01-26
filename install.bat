@ECHO OFF
xcopy lib dist/lib /s /e
copy icon.png dist/icon.png
copy icon.ico dist/icon.ico
copy icon16.ico dist/icon16.ico

PAUSE