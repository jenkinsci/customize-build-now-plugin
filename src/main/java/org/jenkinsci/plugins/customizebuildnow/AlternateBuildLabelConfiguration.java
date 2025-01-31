package org.jenkinsci.plugins.customizebuildnow;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import jenkins.appearance.AppearanceCategory;
import jenkins.model.GlobalConfiguration;
import jenkins.model.GlobalConfigurationCategory;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.StaplerRequest2;

@Symbol("customizeBuildNow")
@Extension
public class AlternateBuildLabelConfiguration extends GlobalConfiguration {

  private transient String alternateBuildNow;

  private transient String alternateBuildWithParams;

  private transient String alternateBuildButton;

  private Labels labels;

  public AlternateBuildLabelConfiguration() {
    load();
  }

  @Override
  public void load() {
    super.load();
    if (labels == null) {
      labels = new Labels();
      labels.setAlternateBuildButton(alternateBuildButton);
      labels.setAlternateBuildNow(alternateBuildNow);
      labels.setAlternateBuildWithParams(alternateBuildWithParams);
    }
  }

  public Labels getLabels() {
    return labels;
  }

  public void setLabels(Labels labels) {
    this.labels = labels;
  }

  public void setAlternateBuildButton(String alternateBuildButton) {
    this.alternateBuildButton = alternateBuildButton;
  }

  @Override
  public boolean configure(StaplerRequest2 req, JSONObject json) throws FormException {
    super.configure(req, json);
    save();
    return true;
  }

  public static AlternateBuildLabelConfiguration get() {
    return GlobalConfiguration.all().get(AlternateBuildLabelConfiguration.class);
  }

  @NonNull
  @Override
  public GlobalConfigurationCategory getCategory() {
    return GlobalConfigurationCategory.get(AppearanceCategory.class);
  }
}
