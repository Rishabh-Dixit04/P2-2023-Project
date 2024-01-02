#include "Rotation.h"
#include "../Pixel.h"
#include <vector>
#include <cmath>

using namespace std;

void rotateImage90(vector<vector<Pixel>> &image)            
{
    int rows = image.size();
    int cols = image[0].size();

    vector<vector<Pixel>> rotatedImage(cols, vector<Pixel>(rows));
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            rotatedImage[j][rows - i - 1] = image[i][j];
        }
    }
    image = rotatedImage;
}

void rotateImage180(vector<vector<Pixel>> &image)
{
    
    for (int k = 0; k < 2; k++)
    {
        int rows = image.size();
        int cols = image[0].size();

        vector<vector<Pixel>> rotatedImage(cols, vector<Pixel>(rows));
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                rotatedImage[j][rows - i - 1] = image[i][j];
            }
        }
        image = rotatedImage;
    }
}

void rotateImage270(vector<vector<Pixel>> &image)
{
    

    for (int k = 0; k < 3; k++)
    {
        int rows = image.size();
     int cols = image[0].size();

        vector<vector<Pixel>> rotatedImage(cols, vector<Pixel>(rows));
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                rotatedImage[j][rows - i - 1] = image[i][j];
            }
        }
        image = rotatedImage;
    }
}

void rotate(vector<vector<Pixel>> &imageVector, int value)          //Calling the corresponding rotate function
{
    if (value == 0)
    {
        return;
    }
    if (value == 90)
    {
        return rotateImage90(imageVector);
    }
    else if (value == 180)
    {
        return rotateImage180(imageVector);
    }
    else if (value == 270)
    {
        return rotateImage270(imageVector);
    }
}
