package Database

import model.Animal

class GlobalVar {
    companion object {
        val STORAGE_PERMISSION_CODE: Int=3
        val listDataAnimal = ArrayList<Animal>()
        val CAMERA_PERMISSION_CODE = 5
    }
}