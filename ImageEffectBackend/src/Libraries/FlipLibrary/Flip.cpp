#include "Flip.h"
using namespace std;

void HorizontalFlip(vector<vector<Pixel>> &image){
    for(vector<Pixel> &row : image) reverse(row.begin(), row.end());        //Flipping row-wise
}

void VerticalFlip(vector<vector<Pixel>> &image){        //Flipping column-wise
    reverse(image.begin(), image.end());
}
