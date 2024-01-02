
#include "Invert.h"
#include<vector>
#include "../Pixel.h"

using namespace std;

void Invert(vector<vector<Pixel>>& imageVector){    //Iterating through all pixels
    for(int i = 0; i < imageVector.size(); i++){    
        for(int j = 0; j < imageVector[i].size(); j++){
            imageVector[i][j].r = 255 - imageVector[i][j].r;        //Inverting color of each pixel
            imageVector[i][j].g = 255 - imageVector[i][j].g;
            imageVector[i][j].b = 255 - imageVector[i][j].b;
        }
    }
}

