package org.example.System;

public interface ClaimStatusUpdater {
    void requestInformation(String comment);
    void proposeClaim();
    void approveClaim();
    void rejectClaim();
}
