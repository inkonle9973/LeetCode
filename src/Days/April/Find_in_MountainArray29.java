package Days.April;

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 */
interface MountainArray {
    public int get(int index);

    public int length();
}

class MountainArrayImpl implements MountainArray {
    private int arr[];
    private int size;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
        this.size = this.arr.length;
    }

    @Override
    public int get(int index) {
        return this.arr[index];
    }

    @Override
    public int length() {
        return this.size;
    }

}


public class Find_in_MountainArray29 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int size = mountainArr.length();
        int mountaintop=findmountaintop(mountainArr,0,size-1);
        int left = findleft(mountainArr, 0, mountaintop,target);
        int right = findright(mountainArr,mountaintop+1,size-1,target);
        return left != -1 ? left : right;
    }



    private int findmountaintop(MountainArray mountainArr, int l, int r) {
        int mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int findleft(MountainArray mountainArr, int l, int r,int target) {
        int mid = 0;
        while (l <= r) {
            mid = l + (r-l)/2;
            if (mountainArr.get(mid) == target) {
                return mid;
            }
            if (mountainArr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid-1 ;
            }
        }
        return -1;
    }

    public int findright(MountainArray mountainArr, int l, int r, int target) {
        int mid = 0;
        while (l <= r) {
            mid = l + (r-l)/2;
            if (mountainArr.get(mid) == target) {
                return mid;
            }
            if (mountainArr.get(mid) > target) {
                l = mid + 1;
            } else {
                r = mid -1;
            }
        }
        return -1;
    }
}
