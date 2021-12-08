# Image Processing
Implemented Image Mosaic

Implemented Image Mosiac by first creating classes and interfaces to deal with seeds
These were ISeed, ISeedling, PixelSeed, Seedlings.

To implement this in harmony with the design I created a Mosaic command in similar 
style to the other commands in the code. Then added a mosaicImage function into the Model,
keeping in line with the style of other functions in the model. I also documented this in the ImageProcessingModel interface. I added a script command to the Controller. Finally I created a Mosaic button in the GUI and based it off the style of the Brighten button for consitency. I also utilized the Mocks to create a few Mosaic tests.

The only issue I ran into was I had trouble keeping up with the logic without use of a Posn class so I made that and used it in my PixelSeed and Seedlings.