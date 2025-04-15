package org.jenkinsci.plugins.customizebuildnow;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

public class Labels extends AbstractDescribableImpl<Labels> {

  private String alternateBuildNow;

  private String alternateBuildWithParams;

  private String alternateBuildButton;

  @DataBoundConstructor
  public Labels() {
  }

  public String getAlternateBuildNow() {
    return alternateBuildNow;
  }

  @DataBoundSetter
  public void setAlternateBuildNow(String alternateBuildNow) {
    this.alternateBuildNow = alternateBuildNow;
  }

  public String getAlternateBuildWithParams() {
    return alternateBuildWithParams;
  }

  @DataBoundSetter
  public void setAlternateBuildWithParams(String alternateBuildWithParams) {
    this.alternateBuildWithParams = alternateBuildWithParams;
  }

  public String getAlternateBuildButton() {
    return alternateBuildButton;
  }

  @DataBoundSetter
  public void setAlternateBuildButton(String alternateBuildButton) {
    this.alternateBuildButton = alternateBuildButton;
  }

  @Extension
  @Symbol("labels")
  public static class DescriptorImpl extends Descriptor<Labels> {

    @NonNull
    @Override
    public String getDisplayName() {
      return "";
    }
  }
}
