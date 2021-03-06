package exa.lnx.a;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class Patches extends Fragment {

    Context context;
    Button button;
    Button button2;
    Button button3;
    TextView textView;
    TextView textView2;
    TextView textView3;
    String patches;
    String s;
    SharedPreferences sharedPreferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        getActivity().setTitle(R.string.patches);

        View view = inflater.inflate(R.layout.patches, container, false);

        context = getActivity().getApplicationContext();
        sharedPreferences = context.getSharedPreferences("GlobalPreferences", 0);

        patches = "Nothing";
        s = Build.SUPPORTED_ABIS[0];

        button = view.findViewById(R.id.button);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);

        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUserToChoosePatches();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(patches.equals("Ashmem")){
                    if(s.equals("arm64-v8a")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/aarch64/install-ashmem.sh && bash install-ashmem.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(s.contains("arm")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/armhf/install-ashmem.sh && bash install-ashmem.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(s.equals("x86")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/i386/install-ashmem.sh && bash install-ashmem.sh");
                        clipboard.setPrimaryClip(clip);
                    }else if(s.equals("x86_64")){
                        ClipData clip = ClipData.newPlainText("Command", "wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/amd64/install-ashmem.sh && bash install-ashmem.sh");
                        clipboard.setPrimaryClip(clip);
                    }
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.termux");
                if(isPackageInstalled("com.termux", context.getPackageManager())){
                    startActivity(intent);
                }else{
                    notifyUserForInstallTerminal();
                }
            }
        });
        return view;
    }
    public void notifyUserToChoosePatches(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.patches_chooser, nullParent);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        if(patches.equals("Ashmem")){
            checkBox.setChecked(true);
        }
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(checkBox.isChecked()){
                    if(!patches.equals("Ashmem")){
                        patches = "Ashmem";
                    }
                }
                if(patches.equals("Ashmem")){
                    if(s.equals("arm64-v8a")){
                        textView.setText("Please scroll down to see all steps.\n\nWARNING: You should have wget installed on linux distro before proceeding, also remember only run this command inside the Linux System and not in Termux, otherwise it will not work!!!\n\nAbout Ashmem: System V shared memory emulation on Android, using ashmem. That includes shmget, shmat, shmdt and shmctl functions. It does not use Binder service, and does not link to Java libraries. It uses Linux sendmsg/recvmsg API instead to transfer file handlers.\n\nWhat it do: If you have error running some GUI programs, install this patch may solve the problem.\n\nHow to use: env LD_PRELOAD=\"/usr/local/lib/libandroid-shmem.so\" <linux_command>");
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/aarch64/install-ashmem.sh && bash install-ashmem.sh \n\n This should install Ashmem on your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to install Patches. Remember: you will need to run ./start-*.sh to run the Linux System before using this command.");
                    }else if(s.contains("arm")){
                        textView.setText("Please scroll down to see all steps.\n\nWARNING: You should have wget installed on linux distro before proceeding, also remember only run this command inside the Linux System and not in Termux, otherwise it will not work!!!\n\nAbout Ashmem: System V shared memory emulation on Android, using ashmem. That includes shmget, shmat, shmdt and shmctl functions. It does not use Binder service, and does not link to Java libraries. It uses Linux sendmsg/recvmsg API instead to transfer file handlers.\n\nWhat it do: If you have error running some GUI programs, install this patch may solve the problem.\n\nHow to use: env LD_PRELOAD=\"/usr/local/lib/libandroid-shmem.so\" <linux_command>");
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/armhf/install-ashmem.sh && bash install-ashmem.sh \n\n This should install Ashmem on your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to install Patches. Remember: you will need to run ./start-*.sh to run the Linux System before using this command.");
                    }else if(s.equals("x86")){
                        textView.setText("Please scroll down to see all steps.\n\nWARNING: You should have wget installed on linux distro before proceeding, also remember only run this command inside the Linux System and not in Termux, otherwise it will not work!!!\n\nAbout Ashmem: System V shared memory emulation on Android, using ashmem. That includes shmget, shmat, shmdt and shmctl functions. It does not use Binder service, and does not link to Java libraries. It uses Linux sendmsg/recvmsg API instead to transfer file handlers.\n\nWhat it do: If you have error running some GUI programs, install this patch may solve the problem.\n\nHow to use: env LD_PRELOAD=\"/usr/local/lib/libandroid-shmem.so\" <linux_command>");
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/i386/install-ashmem.sh && bash install-ashmem.sh \n\n This should install Ashmem on your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to install Patches. Remember: you will need to run ./start-*.sh to run the Linux System before using this command.");
                    }else if(s.equals("x86_64")){
                        textView.setText("Please scroll down to see all steps.\n\nWARNING: You should have wget installed on linux distro before proceeding, also remember only run this command inside the Linux System and not in Termux, otherwise it will not work!!!\n\nAbout Ashmem: System V shared memory emulation on Android, using ashmem. That includes shmget, shmat, shmdt and shmctl functions. It does not use Binder service, and does not link to Java libraries. It uses Linux sendmsg/recvmsg API instead to transfer file handlers.\n\nWhat it do: If you have error running some GUI programs, install this patch may solve the problem.\n\nHow to use: env LD_PRELOAD=\"/usr/local/lib/libandroid-shmem.so\" <linux_command>");
                        textView2.setText("Step 2 : Copy the command to clipboard : wget https://raw.githubusercontent.com/EXALAB/AnLinux-Resources/master/Library/Ashmem/amd64/install-ashmem.sh && bash install-ashmem.sh \n\n This should install Ashmem on your system.");
                        textView3.setText("Step 3 : Start Termux, paste and enter the command to install Patches. Remember: you will need to run ./start-*.sh to run the Linux System before using this command.");
                    }
                }
                button2.setEnabled(true);
                button3.setEnabled(true);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void notifyUserForInstallTerminal(){
        final ViewGroup nullParent = null;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.notify1, nullParent);
        TextView textView = view.findViewById(R.id.textView);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=com.termux");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if(Build.VERSION.SDK_INT >= 21){
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                }
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.termux")));
                }
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
        textView.setText("Termux is not installed, do you want to install it now ?");
    }
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
