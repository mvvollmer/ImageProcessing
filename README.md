# Image Processing 

Packages:

Model:
- Interface ImageProcessingModelState
- Interface ImageProcessingModel extends ImageProcessingModelState
- Class: Model implements ImageProcessingModel

View:
- Interface ImageProcessingView
- Class: View implements ImageProcessingView

Controller:
- Interface ImageProcessingController
- Class: Controller implements ImageProcessingController
- commands: contains the classes for the supported commands following the command design pattern.
Includes interface ImageProcessingCommands containing an "apply" method which all command classes implement.

Util:
- Interface ImageState
- Interface Image extends ImageState
- Class ImageImpl implements Image
- Class ImageProcessingProgram containing the main method
- ImageUtil: utility class utilized for image reading
- Interface Kernel
- Class ImageKernel implements Kernel

Tests:
- ImageProcessingModelTest
- ImageProcessingViewTest
- ImageProcessingControllerTest

Bunny image citation:

- Allanwood, G. (2018, February 16). Photo by Gavin Allanwood on unsplash.
Beautiful Free Images &amp; Pictures. Retrieved November 10, 2021,
from https://unsplash.com/photos/hcxqLJjI99E. 

All other images (dumby, colorful) created by Ryan Duong and Alexander Naishuler.