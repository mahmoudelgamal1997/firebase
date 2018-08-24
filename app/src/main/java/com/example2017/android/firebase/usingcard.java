package com.example2017.android.firebase;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class usingcard extends Fragment {

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_usingcard,null);


        List<questionmodel> list=new ArrayList<>();

        list.add(new questionmodel("كيف يتم استخدام كارت VIP للخصم ؟"," هو عباره عن كارت تخفيضات يضع العديد من قسائم التخفيضات التي تتيح لك استخدامها . تطبق جميع الشروط والاحكام الخاصه بقسائم الشراء مع الالتزام بالقواعد والاحكام الخاصه بكل مكان حيث يقوم عميل VIP بتقديم قسيمه الخصم عند الشراء والاستمتاع بالخدمه طبقا لنسب الخصم الممنوحه في القسيمه او عن طريق الابلكيشن . لا يجوز استخدام دفتر الخصومات مع الابلكيشن والعكس وفي حاله استهلاك الخصومات من الدفتر يعتبر الخصم في الابلكيشن ملغي والعكس صحيح حيث يتم استخدام قسيمه واحده في كل زياره لايجوز استخدام اكثر من قسيمه في الزياره الواحده "));
        list.add(new questionmodel("كيف يتم استخدام Mobile Application ?","\n" +
                "قم بتحميله من علي  google play واستمتع باستخدام البرنامج علي هاتفك المحمول الذي يقدم لك التخفيضات التي يمنحها البرنامج لاعضائه واستخدام الدليل الشامل في كافه المجالات ستجدون في برنامجنا الدليل الشامل الذي يساعدك علي ايجاد المكان الصحيح بافضل اسعار في السوق في قسم العروض وقسم الاعلانات في Mopile Application \n"));


        list.add(new questionmodel("ما هو البرنامج الشامل لشركه VIP GOLDEN CARD ?","هل سبق لك ان قمت بتجميع مبالغ التخفيضات التي حصلت عليها اثناء قيامك بشراء احتياجاتك اليوميه من  قبل ؟ نحن فعلنا ذلك والمجموع كان ضخم .تخيل انك قد حصلت علي قسائم تساوي قيمتها الاف الجنيهات التي تنفقها كل مره تذهب فيها الي التسوق وبغض النظر عما تشتريه سوف تسترد نسبه من ثمن المشتريات في شكل خصومات او مميزات "));
        list.add(new questionmodel("ما هي مميزات كارتVIP للخصم ؟","احصلوا علي مميزات عديده باشتراككم معنا حيث تستطيع ان تسترد نسبه كبيره من نقودك علي هيئه قسائم تخفيضات علي كافه مستلزماتك من ملابس رجالي وحريمي ومطاعم وكفيهات واحذيه ونظارات وهدايا واكسسورات وجيمات ومجالات اخري نقدم اليكم اسلوبنا الجديده للحياه برنامج VIP GOLDEN CARD  تم تصميمه خصيصا لتلبيه احتياجك ."));
        list.add(new questionmodel("كيف يتم التواصل مع شركه VIP GOLDEN CARD ?","يتم التواصل عن طريق موبايل الشركه المتواجد علي كارت الخصم 01125429429 ولمعرفه الشروط والاحكام وصلاحيه هذه البطاقه ."));
        list.add(new questionmodel("ماهي الشروط الصحيحه لاستخدام كارت الخصم VIP GOLDEN CARD ?","1-\tلا يجوز احد استخدام كارت الخصم غير صاحب الكارت .\n" +
                "2-\tلايجوز استخدام الكارت خلال فترات العروض  والتخفيضات واوقات السيل علي بعض المحلات \n" +
                "3-\tلا يجوز استردادها او استبدالها نقدا ويتم استخدام هذه البطاقه او بونات الخصم في عمليه الشراء فقط .\n"));
        list.add( new questionmodel("ماهي طريقه وضع اعلان في Mobile Application ?","يتم الاعلان في التطبيق عن طريق مندوبين التسويق للشركه قم بالاتصال علي رقم الشركه 01125429492 ليصلك مندوبنا "));
        recyclerView = (RecyclerView) view.findViewById(R.id.recycel2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MoviesAdapter moviesAdapter = new MoviesAdapter(list);
        recyclerView.setAdapter(moviesAdapter);



        return view;
    }
}
