Customize Build Now Plugin
==========

This plugin allows users to provide alternate labels to "Build Now", "Build with Parameters" and the "Build" button. 
While it's well understood by Jenkins experts what "Build Now" means, sometimes its more intuitive for new Jenkins users to associate a 
more appropriate label to suit the purpose of the job like "Deploy Now" or "Execute Now" or "Promote Now" etc...

The labels can be configured globally in the `Appearance` section and/or individually for each job.

![Configuration](/docs/config.png)
![Sidepanel](/docs/sidepanel.png)
![Sidepanel with Parameters](/docs/sidepanel-parameters.png)
![Parameters](/docs/parameters.png)


### Configuration as Code
```yaml
appearance:
  customizeBuildNow:
    labels:
      alternateBuildButton: "Execute"
      alternateBuildNow: "Execute Now"
      alternateBuildWithParams: "Execute with Parameters"  
```


### Pipeline Syntax
##### Scripted
```groovy
properties([customizeBuildNow(labels(alternateBuildButton: 'Execute', alternateBuildNow: 'Execute Now', alternateBuildWithParams: 'Execute with Parameters'))])
```

##### Declarative
```groovy
options {
  customizeBuildNow labels(alternateBuildButton: 'Run', alternateBuildNow: 'Run Now', alternateBuildWithParams: 'Run with Parameters')
}
```